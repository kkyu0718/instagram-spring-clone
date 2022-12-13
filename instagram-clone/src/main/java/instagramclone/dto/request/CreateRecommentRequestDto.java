package instagramclone.dto.request;

import instagramclone.domain.UserAccount;

public record CreateRecommentRequestDto(
    Long parentCommentId,
    String content,
    UserAccount userAccount
)
{
    public static CreateRecommentRequestDto of (Long parentCommentId, String content, UserAccount userAccount) {
        return new CreateRecommentRequestDto(parentCommentId, content, userAccount);
    }
}
