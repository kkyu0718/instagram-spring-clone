package instagramclone.dto.request;

public record LoginRequestDto(String email, String password) {

    public static LoginRequestDto of (String email, String password) {
        return new LoginRequestDto(email, password);
    }
}
