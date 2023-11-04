package DSite.service;

import DSite.dto.BoardDto;
import DSite.entity.BoardEntity;
import DSite.response.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BoardService {

    ResponseEntity<BaseResponse> create(BoardDto boardDto) throws IOException;

    ResponseEntity<BaseResponse> getContents(Long id);

    ResponseEntity<BaseResponse> getAllContents();

    ResponseEntity<BaseResponse> getCategoryContents(String category);

    default BoardEntity dtoToEntity(BoardDto dto){
        return BoardEntity.builder()
                .userName(dto.getUserName())
                .url(dto.getUrl())
                .content(dto.getContent())
                .category(dto.getCategory())
                .title(dto.getTitle())
                .image(dto.getImage())
                .description(dto.getDescription())
                .build();
    }
    default BoardDto entityToDto(BoardEntity entity){
        return BoardDto.builder()
                .userName(entity.getUserName())
                .url(entity.getUrl())
                .content(entity.getContent())
                .category(entity.getCategory())
                .title(entity.getTitle())
                .image(entity.getImage())
                .description(entity.getDescription())
                .regDate(entity.getRegDate())
                .build();
    }

}
