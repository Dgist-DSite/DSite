package DSite.domain.Roadmap.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NodeBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nodeId;

    @Column
    private String url;

    @Column
    private String imgUrl;

    @Column
    private String title;

    @Column
    private String description;

    @Builder
    public NodeBoardEntity(String nodeId, String url,String imgUrl, String title, String description) {
        this.url = url;
        this.nodeId = nodeId;
        this.imgUrl = imgUrl;
        this.title = title;
        this.description = description;
    }
    public void fixData(String nodeId, String url, String imgUrl, String title, String description) {
        this.nodeId = nodeId;
        this.url = url;
        this.imgUrl = imgUrl;
        this.title = title;
        this.description = description;
    }
}
