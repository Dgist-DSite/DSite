package DSite.domain.Roadmap.presentation;

import DSite.domain.Roadmap.dto.request.NodeBoardFixRequest;
import DSite.domain.Roadmap.dto.request.NodeBoardRequest;
import DSite.domain.Roadmap.service.NodeBoardService;
import DSite.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roadmap/board")
public class NodeBoardController {
    private final NodeBoardService nodeBoardService;

    @PostMapping("/")
    public ResponseEntity<BaseResponse> create(@RequestBody NodeBoardRequest nodeBoardRequest) throws IOException {
        return nodeBoardService.create(nodeBoardRequest);
    }
    @GetMapping("/{node}")
    public ResponseEntity<BaseResponse> getNodeBoard(@PathVariable String node){ // 노드의 아이디 FK
        return nodeBoardService.getNodeBoard(node);
    }
    @PutMapping("/")
    public ResponseEntity<BaseResponse> fixNodeBoard(@RequestBody NodeBoardFixRequest nodeBoardFixRequest){ // 노드 보드의 아이디
        return nodeBoardService.fixNodeBoard(nodeBoardFixRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteNodeBoard(@PathVariable Long id){ // 노드 보드의 아이디
        return nodeBoardService.delete(id);
    }






}
