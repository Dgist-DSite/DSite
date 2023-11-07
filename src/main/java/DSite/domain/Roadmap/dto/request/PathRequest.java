package DSite.domain.Roadmap.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PathRequest {
    private String startNodeId;
    private String endNodeId;
    private int type;
    private String category;
}

//startNodeId int
//endNodeId int
//type int
