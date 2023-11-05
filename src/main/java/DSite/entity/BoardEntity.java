package DSite.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String url;

    @Column
    private String content;

    @Column
    private String category;

    @Column
    private String title;

    @Column
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private LocalDateTime regDate;

    @Builder
    public BoardEntity(String userName, String url, String content, String category, String title, String image, String description) {
        this.userName = userName;
        this.url = url;
        this.content = content;
        this.category = category;
        this.title = title;
        this.image = image;
        this.description = description;
        this.regDate = LocalDateTime.now();
    }
}
