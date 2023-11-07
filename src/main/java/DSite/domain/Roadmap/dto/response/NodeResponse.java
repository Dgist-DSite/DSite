package DSite.domain.Roadmap.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NodeResponse {
    private Long id;
    private float xPos;
    private float yPos;
    private String text;
    private int nodeType;
    private String category;
}
//nodetable
//id int
//xPos float
//yPos float
//text string
//nodeType int
//String category;