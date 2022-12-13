package instagramclone.dto.response;

import instagramclone.dto.UserAccountDto;

import java.time.LocalDateTime;

public record UserAccountResponseDto(
        Long id,
        String name,
        String email,
        String password,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) {

    public static UserAccountResponseDto from(UserAccountDto dto) {
        return new UserAccountResponseDto(
                dto.id(),
                dto.name(),
                dto.email(),
                dto.password(),
                dto.createdAt(),
                dto.createdBy(),
                dto.modifiedAt(),
                dto.modifiedBy()
                );
    }
}
