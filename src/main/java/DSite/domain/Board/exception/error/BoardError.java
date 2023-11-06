package DSite.domain.Board.exception.error;

import DSite.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardError implements ErrorProperty {
    BOARD_URL_STRANGER(HttpStatus.BAD_REQUEST, "이상한 사이트일 확률이 높아 차단합니다."),
    BOARD_URL_SAME(HttpStatus.CONFLICT, "동일한 URL이 존재합니다.");

    private final HttpStatus status;
    private final String message;
}