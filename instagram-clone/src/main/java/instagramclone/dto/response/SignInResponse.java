package instagramclone.dto.response;

import instagramclone.domain.UserAccount;
import instagramclone.dto.UserAccountDto;

import java.time.LocalDateTime;

public record SignInResponse(
        Long id,
        String name,
        String email,
        String password,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) {

    public static SignInResponse from(UserAccountDto dto) {
        return new SignInResponse(
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
