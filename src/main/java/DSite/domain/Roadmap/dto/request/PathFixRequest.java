package DSite.domain.Roadmap.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PathFixRequest {
    private Long id;
    private String startNodeId;
    private String endNodeId;
    private int type;
    private String category;
}
