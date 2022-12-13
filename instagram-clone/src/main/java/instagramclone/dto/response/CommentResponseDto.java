package instagramclone.dto.response;

import java.time.LocalDateTime;

public record CommentResponseDto(
        Long id,
        Long parentCommentId,
        String content,
        Long feedId,
        UserAccountResponseDto userAccountResponseDto,
        LocalDateTime createdAt,
        String createdBy
) {
    public static CommentResponseDto of (Long id, Long parentCommentId, String content, Long feedId, UserAccountResponseDto userAccountResponseDto, LocalDateTime createdAt, String createdBy) {
        return new CommentResponseDto(id, parentCommentId, content, feedId, userAccountResponseDto, createdAt, createdBy);
    }

    public static CommentResponseDto of (Long id, String content, Long feedId, UserAccountResponseDto userAccountResponseDto, LocalDateTime createdAt, String createdBy) {
        return new CommentResponseDto(id, null, content, feedId, userAccountResponseDto, createdAt, createdBy);
    }
}