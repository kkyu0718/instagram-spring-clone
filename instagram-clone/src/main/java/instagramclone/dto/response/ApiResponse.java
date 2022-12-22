package instagramclone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final int statusCode;
    private final String message;
    private final T data;

    public ApiResponse(ResponseCode responseCode, T data) {
        this.statusCode = responseCode.getStatus().value();
        this.message = responseCode.getMessage();
        this.data = data;
    }

}