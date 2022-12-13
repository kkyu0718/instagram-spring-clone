package instagramclone.dto;

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
}