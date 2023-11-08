package DSite.domain.Roadmap.exception.error;

import DSite.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RoadmapError implements ErrorProperty {

    ROADMAP_ERROR_NOTFOUND(HttpStatus.NOT_FOUND, "Node or Path 를 찾을 수 없습니다."),
    NODE_BOARD_NOTFOUND(HttpStatus.NOT_FOUND, "노드에 Board를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}