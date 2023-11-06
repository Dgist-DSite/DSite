package DSite.global.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public interface ErrorProperty {
    HttpStatus getStatus();
    String getMessage();
}
