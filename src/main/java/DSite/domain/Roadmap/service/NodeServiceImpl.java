package DSite.domain.Roadmap.service;

import DSite.domain.Roadmap.domain.NodeEntity;
import DSite.domain.Roadmap.domain.PathEntity;
import DSite.domain.Roadmap.domain.repository.NodeRepository;
import DSite.domain.Roadmap.domain.repository.PathRepository;
import DSite.domain.Roadmap.dto.request.NodeFixRequest;
import DSite.domain.Roadmap.dto.request.NodeRequest;
import DSite.domain.Roadmap.dto.response.NodeResponse;
import DSite.domain.Roadmap.exception.RoadmapNotFoundException;
import DSite.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NodeServiceImpl implements NodeService{

    private final NodeRepository nodeRepository;
    private final PathRepository pathRepository;

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> create(NodeRequest nodeRequest){
        BaseResponse baseResponse = new BaseResponse();
        nodeRepository.save(requestToEntity(nodeRequest));
        baseResponse.of(HttpStatus.OK, "생성 성공");
        return ResponseEntity.ok(baseResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> getAllContents(String what) {
        BaseResponse baseResponse = new BaseResponse();

        List<NodeEntity> result = nodeRepository.findByCategoryContaining(what);
        List<NodeResponse> nodeResponses = result.stream()
                .map(this::entityToResponse)
                .toList();
        baseResponse.of(HttpStatus.OK, "모든 컨텐츠 불러오기 성공", nodeResponses);

        return ResponseEntity.ok(baseResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> fixContent(NodeFixRequest nodeFixRequest) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<NodeEntity> nodeEntity = nodeRepository.findById(nodeFixRequest.getId());
        if (nodeEntity.isEmpty()) throw RoadmapNotFoundException.EXCEPTION;
        else {
            NodeEntity fixNodeEntity = nodeEntity.get();

            fixNodeEntity.fixXPos(nodeFixRequest.getXPos());
            fixNodeEntity.fixYPos(nodeFixRequest.getYPos());
            fixNodeEntity.fixText(nodeFixRequest.getText());
            fixNodeEntity.fixNodeType(nodeFixRequest.getNodeType());
            fixNodeEntity.fixCategory(nodeFixRequest.getCategory());

            baseResponse.of(HttpStatus.OK, "수정 성공", nodeRepository.save(fixNodeEntity));
            return ResponseEntity.ok(baseResponse);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> deleteContent(String id) {
        BaseResponse baseResponse = new BaseResponse();
        if (nodeRepository.findById(Long.valueOf(id)).isEmpty()) throw RoadmapNotFoundException.EXCEPTION;
        else {

//            Long startNodeId = pathEntity.get().getStartNodeId();
//            Long endNodeId = pathEntity.get().getEndNodeId();
//
//            List<PathEntity> pathEntitiesStart  = pathRepository.findByStartNodeIdContaining(startNodeId);
//            pathEntitiesStart.forEach(i ->{
//                pathRepository.deleteById(i.getId());
//            });
//            List<PathEntity> pathEntitiesEnd = pathRepository.findByEndNodeIdContaining(endNodeId);
//            pathEntitiesEnd.forEach(i ->{
//                pathRepository.deleteById(i.getId());
//            });

            pathRepository.deleteByEndNodeIdContaining(id);
            pathRepository.deleteByStartNodeIdContaining(id);
            //System.out.println("=====================================\n" + nodeEntity.get().getId() +"=====================================\n");
            nodeRepository.deleteById(Long.valueOf(id));

            baseResponse.of(HttpStatus.OK, "삭제 성공");
            return ResponseEntity.ok(baseResponse);
        }

    }

}
