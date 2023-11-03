package DSite.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BoardDto {

    private String userName;
    private String url;
    private String content;
    private String category;

    private LocalDateTime regDate;

}
