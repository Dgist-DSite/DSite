package DSite.domain.Roadmap.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PathResponse {
    private Long id;
    private Long startNodeId;
    private Long endNodeId;
    private int type;
    private String category;
}
