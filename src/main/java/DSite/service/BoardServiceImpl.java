package DSite.service;

import DSite.dto.BoardDto;
import DSite.entity.BoardEntity;
import DSite.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;


    @Override
    public void create(BoardDto boardDto) {
        System.out.println(boardDto.getContent());
        BoardEntity boardEntity = dtoToEntity(boardDto);
        boardRepository.save(boardEntity);
       // System.out.println(boardEntity.getCategory() + boardEntity.getContent() + boardEntity.getUrl());
    }

    @Override
    public Map<String , List<BoardDto>> getAllContent() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtos = boardEntities.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());

        //역순으로 데이터 반환
        Collections.reverse(boardDtos);

        Map<String, List<BoardDto>> resultMap = new HashMap<>();
        resultMap.put("data", boardDtos);

        return resultMap;
    }

    @Override
    public Map<String, List<BoardDto>> getCategoryContent(String category) {
       List<BoardEntity> boardEntity = boardRepository.findByCategoryContaining(category);
       List<BoardDto> boardDtos = boardEntity.stream()
               .map(this::entityToDto)
               .collect(Collectors.toList());
       Collections.reverse(boardDtos);

       Map<String,List<BoardDto>> resultMap = new HashMap<>();
        resultMap.put("data", boardDtos);

       return resultMap;
    }


}
