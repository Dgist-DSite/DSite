package DSite.controller;

import DSite.dto.BoardDto;
import DSite.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @PostMapping("/create")
    public void create(@RequestBody BoardDto boardDto){
        boardService.create(boardDto);
    }

    @GetMapping("/all")
    public Map<String, List<BoardDto>> getAllContent(){
        return boardService.getAllContent();
    }

    @GetMapping("/category")
    public Map<String, List<BoardDto>> getCategoryContent(@RequestParam("what") String category){
        return boardService.getCategoryContent(category);
    }
}
