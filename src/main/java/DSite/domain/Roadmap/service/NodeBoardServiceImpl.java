package DSite.domain.Roadmap.service;

import DSite.domain.Board.exception.BoardUrlStrangeException;
import DSite.domain.Roadmap.domain.NodeBoardEntity;
import DSite.domain.Roadmap.domain.repository.NodeBoardRepository;
import DSite.domain.Roadmap.dto.NodeBoardDto;
import DSite.domain.Roadmap.dto.request.NodeBoardFixRequest;
import DSite.domain.Roadmap.dto.request.NodeBoardRequest;
import DSite.domain.Roadmap.dto.response.NodeBoardResponse;
import DSite.domain.Roadmap.exception.NodeBoardNotFoundException;
import DSite.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NodeBoardServiceImpl implements NodeBoardService {

    private final NodeBoardRepository nodeBoardRepository;

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> create(NodeBoardRequest boardRequest) throws IOException {
        BaseResponse baseResponse = new BaseResponse();
        NodeBoardDto nodeBoardDto = new NodeBoardDto();
        nodeBoardDto.setNodeId(boardRequest.getNodeId());
        nodeBoardDto.setUrl(boardRequest.getUrl());
        Document document = Jsoup.connect(nodeBoardDto.getUrl()).get();
        String ogTitle = document.select("meta[property=og:title]").attr("content"); //제목
        String ogImage = document.select("meta[property=og:image]").attr("content"); //이미지
        String ogDescription = document.select("meta[property=og:description]").attr("content"); //설명
        if (ogTitle.isEmpty() && ogDescription.isEmpty()) throw BoardUrlStrangeException.EXCEPTION;
        else {
            if (ogImage.isEmpty()) {
                nodeBoardDto.setImgUrl("https://hook-s3-innosync.s3.ap-northeast-2.amazonaws.com/images/Group+50.png");
            } else {
                nodeBoardDto.setImgUrl(ogImage);
            }
            nodeBoardDto.setTitle(ogTitle);
            nodeBoardDto.setDescription(ogDescription);
            NodeBoardEntity nodeBoardEntity = dtoToEntity(nodeBoardDto);

            nodeBoardRepository.save(nodeBoardEntity);
            List<NodeBoardEntity> nodeBoardEntities= nodeBoardRepository.findNodeBoardEntityByNodeIdContaining(boardRequest.getNodeId());

            List<NodeBoardResponse> nodeBoardResponses = nodeBoardEntities.stream()
                            .map(this::entityToResponse)
                            .toList();

            baseResponse.of(HttpStatus.OK, "저장성공", nodeBoardResponses);
            return ResponseEntity.ok(baseResponse);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getNodeBoard(String node) {
        BaseResponse baseResponse = new BaseResponse();
        List<NodeBoardEntity> result= nodeBoardRepository.findNodeBoardEntityByNodeIdContaining(node);

        if (result.isEmpty()) throw NodeBoardNotFoundException.EXCEPTION;
        List<NodeBoardResponse> nodeBoardResponses = result.stream()
                .map(this::entityToResponse)
                .toList();

        baseResponse.of(HttpStatus.OK, "불러오기 성공", nodeBoardResponses);
        return ResponseEntity.ok(baseResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> fixNodeBoard(NodeBoardFixRequest nodeBoardFixRequest) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<NodeBoardEntity> nodeBoardEntity = nodeBoardRepository.findById(nodeBoardFixRequest.getId());
        if (nodeBoardEntity.isEmpty()) throw NodeBoardNotFoundException.EXCEPTION;

        nodeBoardEntity.get().fixData(
                nodeBoardFixRequest.getNodeId(),
                nodeBoardFixRequest.getUrl(),
                nodeBoardFixRequest.getImgUrl(),
                nodeBoardFixRequest.getTitle(),
                nodeBoardFixRequest.getDescription()
        );
        baseResponse.of(HttpStatus.OK, "성공");
        return ResponseEntity.ok(baseResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> delete(Long id) {
        BaseResponse baseResponse = new BaseResponse();
        if (nodeBoardRepository.findById(id).isEmpty()) throw NodeBoardNotFoundException.EXCEPTION;
        nodeBoardRepository.deleteById(id);
        baseResponse.of(HttpStatus.OK, "삭제성공");
        return ResponseEntity.ok(baseResponse);
    }
}
