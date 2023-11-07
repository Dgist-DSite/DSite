package DSite.domain.Roadmap.service;

import DSite.domain.Roadmap.domain.NodeEntity;
import DSite.domain.Roadmap.domain.PathEntity;
import DSite.domain.Roadmap.domain.repository.PathRepository;
import DSite.domain.Roadmap.dto.request.PathFixRequest;
import DSite.domain.Roadmap.dto.request.PathRequest;
import DSite.domain.Roadmap.dto.response.NodeResponse;
import DSite.domain.Roadmap.dto.response.PathResponse;
import DSite.domain.Roadmap.exception.RoadmapNotFoundException;
import DSite.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PathServiceImpl implements PathService{

    private final PathRepository pathRepository;

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> create(PathRequest pathRequest) {
        BaseResponse baseResponse = new BaseResponse();
        pathRepository.save(requestToEntity(pathRequest));
        baseResponse.of(HttpStatus.OK, "저장 성공");
        return ResponseEntity.ok(baseResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> getAllContents(String what) {
        BaseResponse baseResponse = new BaseResponse();

        List<PathEntity> result = pathRepository.findByCategoryContaining(what);
        List<PathResponse> pathResponses = result.stream()
                .map(this::entityToResponse)
                .toList();
        baseResponse.of(HttpStatus.OK, "모든 컨텐츠 불러오기 성공", pathResponses);

        return ResponseEntity.ok(baseResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> fixContent(PathFixRequest pathFixRequest) {
        BaseResponse baseResponse = new BaseResponse();
        if(pathRepository.findById(pathFixRequest.getId()).isEmpty()) throw RoadmapNotFoundException.EXCEPTION;
        Optional<PathEntity> result = pathRepository.findById(pathFixRequest.getId());
        PathEntity pathEntity = result.get();
        pathEntity.fixData(
                pathFixRequest.getId(),
                pathEntity.getStartNodeId(),
                pathEntity.getStartNodeId(),
                pathEntity.getType(),
                pathEntity.getCategory()
        );
        pathRepository.save(pathEntity);
        baseResponse.of(HttpStatus.OK, "수정 성공");
        return ResponseEntity.ok(baseResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> deleteContent(Long id) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<PathEntity> pathEntity = pathRepository.findById(id);
        if(pathEntity.isEmpty()) throw RoadmapNotFoundException.EXCEPTION;
        else{

            pathRepository.deleteById(id);

            baseResponse.of(HttpStatus.OK, "삭제 성공");
            return ResponseEntity.ok(baseResponse);
        }

    }
}
