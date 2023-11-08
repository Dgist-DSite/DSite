package DSite.domain.Roadmap.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NodeBoardResponse {
    private Long id;
    private String nodeId;
    private String url;
    private String imgUrl;
    private String title;
    private String description;
}
