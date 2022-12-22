package instagramclone.dto.response;

import instagramclone.dto.CommentDto;

import java.time.LocalDateTime;

public record CommentResponseDto(
        Long id,
        Long parentCommentId,
        String content,
        Long feedId,
        LocalDateTime createdAt,
        String createdBy
) {
    public static CommentResponseDto of (Long id, Long parentCommentId, String content, Long feedId, LocalDateTime createdAt, String createdBy) {
        return new CommentResponseDto(id, parentCommentId, content, feedId, createdAt, createdBy);
    }

    public static CommentResponseDto of (Long id, String content, Long feedId, LocalDateTime createdAt, String createdBy) {
        return new CommentResponseDto(id, null, content, feedId, createdAt, createdBy);
    }

    public static CommentResponseDto from(CommentDto dto) {
        return new CommentResponseDto(dto.id(), dto.parentCommentId(), dto.content(), dto.feedId(), dto.createdAt(), dto.createdBy());
    }
}