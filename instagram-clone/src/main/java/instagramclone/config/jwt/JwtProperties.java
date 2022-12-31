package instagramclone.config.jwt;

/**
 * JWT 기본 설정값
 */
public class JwtProperties {
    public static final int EXPIRATION_TIME = 600000; // 10분
    public static final String HEADER_NAME = "JWT-AUTHENTICATION";
}