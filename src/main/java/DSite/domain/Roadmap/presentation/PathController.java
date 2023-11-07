package DSite.domain.Roadmap.presentation;

import DSite.domain.Roadmap.dto.request.PathFixRequest;
import DSite.domain.Roadmap.dto.request.PathRequest;
import DSite.domain.Roadmap.service.PathService;
import DSite.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roadmap/path")
public class PathController {
    private final PathService pathService;

    @PostMapping("/")
    public ResponseEntity<BaseResponse> create(@RequestBody PathRequest pathRequest){
        return pathService.create(pathRequest);
    }

    @GetMapping("/category")
    public ResponseEntity<BaseResponse> getAllContents(@RequestParam("what") String what){
        return pathService.getAllContents(what);
    }

    @PutMapping("/")
    public ResponseEntity<BaseResponse> fixContent(@RequestBody PathFixRequest pathFixRequest){
        return pathService.fixContent(pathFixRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteContent(@PathVariable Long id){
        return pathService.deleteContent(id);
    }


}
