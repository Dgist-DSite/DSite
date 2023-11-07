package DSite.domain.Roadmap.service;

import DSite.domain.Roadmap.domain.NodeEntity;
import DSite.domain.Roadmap.dto.request.NodeFixRequest;
import DSite.domain.Roadmap.dto.request.NodeRequest;
import DSite.domain.Roadmap.dto.response.NodeResponse;
import DSite.global.common.dto.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface NodeService {
    ResponseEntity<BaseResponse> create(NodeRequest nodeRequest);

    ResponseEntity<BaseResponse> getAllContents(String what);

    ResponseEntity<BaseResponse> fixContent(NodeFixRequest nodeFixRequest);

    ResponseEntity<BaseResponse> deleteContent(String id);


    default NodeEntity requestToEntity(NodeRequest nodeRequest){
        return NodeEntity.builder()
                .xPos(nodeRequest.getXPos())
                .yPos(nodeRequest.getYPos())
                .text(nodeRequest.getText())
                .nodeType(nodeRequest.getNodeType())
                .category(nodeRequest.getCategory())
                .build();
    }
    default NodeResponse entityToResponse(NodeEntity nodeEntity){
        return NodeResponse.builder()
                .id(nodeEntity.getId())
                .xPos(nodeEntity.getXPos())
                .yPos(nodeEntity.getYPos())
                .text(nodeEntity.getText())
                .nodeType(nodeEntity.getNodeType())
                .category(nodeEntity.getCategory())
                .build();
    }

}
