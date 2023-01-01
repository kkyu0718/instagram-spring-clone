package instagramclone.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import instagramclone.domain.UserAccount;
import instagramclone.dto.request.LoginRequestDto;
import instagramclone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT를 이용한 로그인 인증
 */
@Slf4j
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(
            "/api/auth/login", HttpMethod.POST.name());

    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider, UserRepository userRepository,
            AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
        setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler(
                jwtTokenProvider, userRepository, objectMapper));
        this.objectMapper = objectMapper;
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
        log.info("Authentication Filter");
        log.info("request {}", request);
        ObjectMapper om = new ObjectMapper();
        LoginRequestDto loginRequestDto = null;
        try {
            loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("user : {}", loginRequestDto);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDto.email(),
                loginRequestDto.password()
        );
        Authentication authentication = getAuthenticationManager().authenticate(authenticationToken);
        return authentication;
    }

//    /**
//     * 인증에 성공했을 때 사용
//     * JWT Token을 생성해서 쿠키에 넣는다.
//     */
//    @Override
//    protected void successfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain,
//            Authentication authResult
//    ) throws IOException {
//        UserAccount user = (UserAccount) authResult.getPrincipal();
//        String token = JwtUtils.createToken(user);
////        response.getWriter().write("Bearer " + token);
////        response.getWriter().flush();
//        // 쿠키 생성
//        Cookie cookie = new Cookie(JwtProperties.HEADER_NAME, token);
//        cookie.setMaxAge(JwtProperties.EXPIRATION_TIME); // 쿠키의 만료시간 설정
//        cookie.setPath("/");
//        response.addCookie(cookie);
////        response.sendRedirect("/");
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException failed
//    ) throws IOException {
//        response.sendError(401, "UNAUTHORIZED_ERROR");
//    }
}