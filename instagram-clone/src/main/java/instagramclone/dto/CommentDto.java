package instagramclone.dto;

import instagramclone.domain.Comment;

import java.time.LocalDateTime;


public record CommentDto(
        Long id,
        Long parentCommentId,
        String content,
        Long feedId,
        UserAccountDto userAccount,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)
{
    public static CommentDto of(Long parentCommentId, String content, Long feedId, UserAccountDto userAccountDto) {
        return new CommentDto(null, parentCommentId, content, feedId, userAccountDto, null, null, null, null);
    }

    public static CommentDto of(String content, Long feedId, UserAccountDto userAccountDto) {
        return new CommentDto(null, null, content, feedId, userAccountDto, null, null, null, null);
    }

    public static CommentDto from (Comment entity) {
        return new CommentDto(
                entity.getId(),
                entity.getParentCommentId(),
                entity.getContent(),
                entity.getFeed().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}