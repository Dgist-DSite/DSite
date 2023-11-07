package DSite.domain.Roadmap.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float xPos;

    @Column
    private float yPos;

    @Column
    private String text;

    @Column
    private int nodeType;

    @Column
    private String category;

    @OneToMany(mappedBy = "nodeEntity", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<PathEntity> pathEntities = new ArrayList<>();

    @Builder
    public NodeEntity(float xPos, float yPos, String text, int nodeType, String category) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.text = text;
        this.nodeType = nodeType;
        this.category = category;
    }

    public void fixXPos(float xPos) {
        this.xPos = xPos;
    }

    public void fixYPos(float yPos) {
        this.yPos = yPos;
    }

    public void fixText(String text) {
        this.text = text;
    }

    public void fixNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    public void fixCategory(String category) {
        this.category = category;
    }
}


//nodetable
//id int
//xPos float
//yPos float
//text string
//nodeType int
//String category;