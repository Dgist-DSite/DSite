package DSite.global.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class BaseResponse {

    private HttpStatus status;
    private Object message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public BaseResponse() {
        this.status=HttpStatus.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }

    public void of(HttpStatus status, Object message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public void of(HttpStatus status, Object message){
        this.status = status;
        this.message = message;
    }
}
