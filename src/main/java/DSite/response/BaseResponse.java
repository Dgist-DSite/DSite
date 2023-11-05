package DSite.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

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
}
