package instagramclone.dto.request;

import instagramclone.dto.UserAccountDto;

public record SignInRequest(String name, String email, String password) {
    public static SignInRequest of (String name, String email, String password) {
        return new SignInRequest(name, email, password);
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(name, email, password);
    }
}
