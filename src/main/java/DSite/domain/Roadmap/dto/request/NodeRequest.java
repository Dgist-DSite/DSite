package DSite.domain.Roadmap.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeRequest {
    private float xPos;
    private float yPos;
    private String text;
    private int nodeType;
    private String category;
}

//xPos float
//yPos float
//text string
//nodeType int
//String category;