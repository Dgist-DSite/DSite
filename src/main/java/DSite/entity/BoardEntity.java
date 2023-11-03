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
    private LocalDateTime regDate;

    @Builder
    public BoardEntity(String userName, String content, String url, String category) {
        this.userName = userName;
        this.content = content;
        this.url = url;
        this.category = category;
        this.regDate=LocalDateTime.now();
    }
}
