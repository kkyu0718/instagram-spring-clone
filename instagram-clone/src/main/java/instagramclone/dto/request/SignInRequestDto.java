package instagramclone.dto.request;

import instagramclone.dto.UserAccountDto;

public record SignInRequestDto(String name, String email, String password) {
    public static SignInRequestDto of (String name, String email, String password) {
        return new SignInRequestDto(name, email, password);
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(name, email, password);
    }
}
