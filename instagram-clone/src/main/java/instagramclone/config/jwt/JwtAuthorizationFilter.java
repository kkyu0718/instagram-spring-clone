package instagramclone.config.jwt;

import instagramclone.domain.UserAccount;
import instagramclone.exception.CustomException;
import instagramclone.exception.ErrorCode;
import instagramclone.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static org.springframework.security.core.context.SecurityContextHolder.*;

/**
 * JWT를 이용한 인증
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String token = null;
        try {
            // header 에서 JWT token을 가져옵니다.
            token = request.getHeader(JwtProperties.HEADER_NAME);
        } catch (Exception ignored) {
        }
        if (token != null) {
            try {
                Authentication authentication = getUsernamePasswordAuthenticationToken(token);
                getContext().setAuthentication(authentication);
            } catch (Exception e) {
                Cookie cookie = new Cookie(JwtProperties.HEADER_NAME, null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * JWT 토큰으로 User를 찾아서 UsernamePasswordAuthenticationToken를 만들어서 반환한다.
     * User가 없다면 null
     */
    private Authentication getUsernamePasswordAuthenticationToken(String token) {
        String userId = JwtUtils.getUserId(token);
        if (userId != null) {
            UserAccount user = userRepository.findById(Long.valueOf(userId))
                    .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));
            return new UsernamePasswordAuthenticationToken(
                    user, // principal
                    null,
                    user.getAuthorities()
            );
        }
        return null; // 유저가 없으면 NULL
    }
}