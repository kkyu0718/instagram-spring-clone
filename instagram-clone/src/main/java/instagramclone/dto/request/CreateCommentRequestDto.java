package instagramclone.dto.request;

import instagramclone.domain.UserAccount;

public record CreateCommentRequestDto(
   String content,
   Long feedId,
   Long parentCommentId

) {
    public static CreateCommentRequestDto of (String content, Long feedId) {
        return new CreateCommentRequestDto(content, feedId, null);
    }

    public static CreateCommentRequestDto of (String content, Long feedId, Long parentCommentId) {
        return new CreateCommentRequestDto(content, feedId, parentCommentId);
    }
}
