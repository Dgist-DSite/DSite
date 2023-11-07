package DSite.domain.Roadmap.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PathEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long startNodeId;

    @Column
    private Long endNodeId;

    @Column
    private int type;

    @Column
    private String category;

    @Builder
    public PathEntity(Long startNodeId, Long endNodeId, int type, String category) {
        this.startNodeId = startNodeId;
        this.endNodeId = endNodeId;
        this.type = type;
        this.category = category;
    }

    public void fixData(Long id, Long startNodeId, Long endNodeId, int type, String category){
        this.id=id;
        this.startNodeId = startNodeId;
        this.endNodeId = endNodeId;
        this.type = type;
        this.category = category;
    }
}

//pathtable
//id int
//startNodeId int
//endNodeId int
// type int