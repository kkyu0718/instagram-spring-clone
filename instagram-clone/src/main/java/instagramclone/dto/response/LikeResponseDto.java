package instagramclone.dto.response;

import java.time.LocalDateTime;

public record LikeResponseDto(
        Long id,
        Long feedId,
        UserAccountResponseDto userAccountResponseDto,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {
    public static LikeResponseDto of (Long id, Long feedId, UserAccountResponseDto userAccountResponseDto, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new LikeResponseDto(id, feedId, userAccountResponseDto, createdAt, createdBy, modifiedAt, modifiedBy);
    }
}