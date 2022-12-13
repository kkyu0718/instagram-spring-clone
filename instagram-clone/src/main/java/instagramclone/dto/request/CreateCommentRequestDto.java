package instagramclone.dto.request;

import instagramclone.domain.UserAccount;

public record CreateCommentRequestDto(
   String content,
   Long feedId,
   UserAccount userAccount
) {
    public static CreateCommentRequestDto of (String content, Long feedId, UserAccount userAccount) {
        return new CreateCommentRequestDto(content, feedId, userAccount)
    }
}
