package DSite.domain.Board.presentation;

import DSite.domain.Board.dto.BoardDto;
import DSite.global.common.dto.BaseResponse;
import DSite.domain.Board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://dsite.vercel.app", "http://localhost:3000"})
public class BoardController {

    private final BoardService boardService;


    @PostMapping("/create")
    public ResponseEntity<BaseResponse> create(@RequestBody BoardDto boardDto) throws IOException {
        return boardService.create(boardDto);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<BaseResponse> getContents(@PathVariable Long id){
        return boardService.getContents(id);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllContents(){
        return boardService.getAllContents();
    }

    @GetMapping("/category")
    public ResponseEntity<BaseResponse> getCategoryContents(@RequestParam("what") String category){
        return boardService.getCategoryContents(category);
    }
}
