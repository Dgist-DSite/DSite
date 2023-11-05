package DSite.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BoardDto {

    private Long id;
    private String userName;
    private String url;
    private String content;
    private String category;
    private String title;
    private String image;
    private String description;

    private LocalDateTime regDate;

}
