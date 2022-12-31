package instagramclone.config.jwt;

import instagramclone.domain.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JWT를 이용한 로그인 인증
 * https://velog.io/@chullll/Spring-Security-JWT-%ED%95%84%ED%84%B0-%EC%A0%81%EC%9A%A9-%EA%B3%BC%EC%A0%95
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    /**
     * 로그인 인증 시도
     */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        // 로그인할 때 입력한 email과 password를 가지고 authenticationToken를 생성한다.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getParameter("email"),
                request.getParameter("password"),
                new ArrayList<>()
        );
        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * 인증에 성공했을 때 사용
     * JWT Token을 생성해서 쿠키에 넣는다.
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException {
        UserAccount user = (UserAccount) authResult.getPrincipal();
        String token = JwtUtils.createToken(user);
        // 쿠키 생성
        Cookie cookie = new Cookie(JwtProperties.HEADER_NAME, token);
        cookie.setMaxAge(JwtProperties.EXPIRATION_TIME); // 쿠키의 만료시간 설정
        cookie.setPath("/");
        response.addCookie(cookie);
//        response.sendRedirect("/");
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException {
        response.sendError(401, "UNAUTHORIZED_ERROR");
    }
}