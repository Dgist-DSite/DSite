package DSite.domain.Roadmap.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(columnDefinition = "TEXT")
    private String description;
    @Builder
    public NodeEntity(float xPos, float yPos, String text, int nodeType, String category, String description) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.text = text;
        this.nodeType = nodeType;
        this.category = category;
        this.description = description;
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

    public void fixDescription(String description) {
        this.description = description;
    }
}


//nodetable
//id int
//xPos float
//yPos float
//text string
//nodeType int
//String category;