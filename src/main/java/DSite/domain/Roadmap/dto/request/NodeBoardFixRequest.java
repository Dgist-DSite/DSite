package DSite.domain.Roadmap.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeBoardFixRequest {
    private Long id;
    private String nodeId;
    private String url;
    private String imgUrl;
    private String title;
    private String description;
}
