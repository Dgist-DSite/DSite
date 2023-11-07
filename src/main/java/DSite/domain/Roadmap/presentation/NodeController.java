package DSite.domain.Roadmap.presentation;

import DSite.domain.Roadmap.dto.request.NodeFixRequest;
import DSite.domain.Roadmap.dto.request.NodeRequest;
import DSite.domain.Roadmap.service.NodeService;
import DSite.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roadmap/node")
public class NodeController {
    private final NodeService nodeService;

    @PostMapping("/")
    public ResponseEntity<BaseResponse> create(@RequestBody NodeRequest request){
        return nodeService.create(request);
    }
    @GetMapping("/category")
    public ResponseEntity<BaseResponse> read(@RequestParam("what") String what){
        return nodeService.getAllContents(what);
    }
    @PutMapping("/")
    public ResponseEntity<BaseResponse> fixContent(@RequestBody NodeFixRequest request){
        return nodeService.fixContent(request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteContent(@PathVariable Long id){
        return nodeService.deleteContent(id);
    }


}
