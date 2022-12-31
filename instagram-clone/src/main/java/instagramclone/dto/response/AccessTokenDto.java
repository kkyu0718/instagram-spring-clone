package instagramclone.dto.response;

public record AccessTokenDto(String accessToken) {
    public static AccessTokenDto of(String token) {
        return new AccessTokenDto(token);
    }
}
