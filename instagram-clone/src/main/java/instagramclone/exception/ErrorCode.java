package instagramclone.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 공통
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "접근 권한이 없는 유저입니다."),

    // user
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "중복된 이메일 입니다."),
    LOGIN_FAILURE(HttpStatus.NOT_FOUND, "로그인에 실패하였습니다."),

    // feed
    FEED_NOT_CREATED(HttpStatus.BAD_REQUEST, "피드 저장에 실패하였습니다."),
    FEED_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 피드입니다."),

    // comment
    COMMENT_NOT_CREATED(HttpStatus.BAD_REQUEST, "답글 저장에 실패하였습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 답글 입니다."),

    // like
    LIKE_NOT_CREATED(HttpStatus.BAD_REQUEST, "좋아요 저장에 실패하였습니다."),
    LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 좋아요 입니다." );

    private final HttpStatus status;
    private final String message;

}