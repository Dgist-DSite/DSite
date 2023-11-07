package DSite.domain.Roadmap.service;

import DSite.domain.Roadmap.domain.PathEntity;
import DSite.domain.Roadmap.dto.request.PathFixRequest;
import DSite.domain.Roadmap.dto.request.PathRequest;
import DSite.domain.Roadmap.dto.response.PathResponse;
import DSite.global.common.dto.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface PathService {

    ResponseEntity<BaseResponse> create(PathRequest pathRequest);

    ResponseEntity<BaseResponse> getAllContents(String what);

    ResponseEntity<BaseResponse> fixContent(PathFixRequest pathFixRequest);

    ResponseEntity<BaseResponse> deleteContent(Long id);

    default PathEntity requestToEntity(PathRequest pathRequest){
        return PathEntity.builder()
                .startNodeId(pathRequest.getStartNodeId())
                .endNodeId(pathRequest.getEndNodeId())
                .type(pathRequest.getType())
                .category(pathRequest.getCategory())
                .build();
    }
    default PathResponse entityToResponse(PathEntity pathEntity){
        return PathResponse.builder()
                .id(pathEntity.getId())
                .startNodeId(pathEntity.getStartNodeId())
                .endNodeId(pathEntity.getEndNodeId())
                .type(pathEntity.getType())
                .category(pathEntity.getCategory())
                .build();
    }

}
