package DSite.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse {

    private HttpStatus status;
    private Object message;
    private Object data;

    public BaseResponse() {
        this.status=HttpStatus.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}
