package DSite.service;

import DSite.dto.BoardDto;
import DSite.entity.BoardEntity;

import java.util.List;
import java.util.Map;

public interface BoardService {

    void create(BoardDto boardDto);

    Map<String, List<BoardDto>> getAllContent();

    Map<String, List<BoardDto>> getCategoryContent(String category);

    default BoardEntity dtoToEntity(BoardDto dto){
        return BoardEntity.builder()
                .userName(dto.getUserName())
                .url(dto.getUrl())
                .content(dto.getContent())
                .category(dto.getCategory())
                .build();
    }
    default BoardDto entityToDto(BoardEntity entity){
        return BoardDto.builder()
                .userName(entity.getUserName())
                .url(entity.getUrl())
                .content(entity.getContent())
                .category(entity.getCategory())
                .build();
    }

}
