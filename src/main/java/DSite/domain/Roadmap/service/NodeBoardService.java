package DSite.domain.Roadmap.service;

import DSite.domain.Roadmap.domain.NodeBoardEntity;
import DSite.domain.Roadmap.dto.NodeBoardDto;
import DSite.domain.Roadmap.dto.request.NodeBoardFixRequest;
import DSite.domain.Roadmap.dto.request.NodeBoardRequest;
import DSite.domain.Roadmap.dto.response.NodeBoardResponse;
import DSite.global.common.dto.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface NodeBoardService {
    ResponseEntity<BaseResponse> create(NodeBoardRequest boardRequest);
    ResponseEntity<BaseResponse> getNodeBoard(String node);
    ResponseEntity<BaseResponse> fixNodeBoard(NodeBoardFixRequest nodeBoardFixRequest);
    ResponseEntity<BaseResponse> delete(Long id);

    default NodeBoardEntity dtoToEntity(NodeBoardDto nodeBoardDto){
        return NodeBoardEntity.builder()
                .nodeId(nodeBoardDto.getNodeId())
                .url(nodeBoardDto.getUrl())
                .title(nodeBoardDto.getTitle())
                .imgUrl(nodeBoardDto.getImgUrl())
                .description(nodeBoardDto.getDescription())
                .build();
    }
    default NodeBoardResponse entityToResponse(NodeBoardEntity nodeBoardEntity){
        return NodeBoardResponse.builder()
                .id(nodeBoardEntity.getId())
                .nodeId(nodeBoardEntity.getNodeId())
                .url(nodeBoardEntity.getUrl())
                .title(nodeBoardEntity.getTitle())
                .imgUrl(nodeBoardEntity.getImgUrl())
                .description(nodeBoardEntity.getDescription())
                .build();
    }

}
