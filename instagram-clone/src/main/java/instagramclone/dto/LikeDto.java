package instagramclone.dto;

import instagramclone.domain.Like;

import java.io.Serializable;
import java.time.LocalDateTime;

public record LikeDto(
        Long id,
        Long feedId,
        UserAccountDto userAccount,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static LikeDto of (Long id, Long feedId, UserAccountDto userAccountDto, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new LikeDto(id, feedId, userAccountDto, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static LikeDto from(Like entity) {
        return new LikeDto(
                entity.getId(),
                entity.getFeed().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}