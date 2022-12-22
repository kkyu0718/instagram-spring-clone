package instagramclone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    // user
    USER_CREATED(HttpStatus.CREATED, "유저 회원가입에 성공하였습니다."),
    USER_LOGIN_SUCCESS(HttpStatus.OK, "유저 로그인에 성공하였습니다."),

    // comment
    COMMENT_CREATED(HttpStatus.CREATED, "댓글 생성에 성공하였습니다."),
    COMMENT_FOUND(HttpStatus.OK, "댓글 조회에 성공하였습니다." ),

    // feed
    FEED_CREATED(HttpStatus.CREATED, "피드 생성에 성공하였습니다."),
    FEED_FOUND(HttpStatus.OK, "피드 조회에 성공하였습니다."),

    // like
    LIKE_CREATED(HttpStatus.CREATED, "좋아요 생성에 성공하였습니다."),
    LIKE_DELETED(HttpStatus.OK, "좋아요 삭제에 성공하였습니다.");


    private final HttpStatus status;
    private final String message;

}