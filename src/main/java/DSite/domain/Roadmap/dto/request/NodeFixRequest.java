package DSite.domain.Roadmap.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeFixRequest {
    private Long id;
    private float xPos;
    private float yPos;
    private String text;
    private int nodeType;
    private String category;
    private String description;
}
