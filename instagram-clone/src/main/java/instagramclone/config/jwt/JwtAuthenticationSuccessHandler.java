package instagramclone.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import instagramclone.domain.UserAccount;
import instagramclone.dto.response.AccessTokenDto;
import instagramclone.dto.response.ApiResponse;
import instagramclone.dto.response.ResponseCode;
import instagramclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String username = authentication.getName();
        log.info("onAuthenticationSuccess {}", username);
        String accessToken = jwtTokenProvider.createAccessToken(username);
        String refreshToken = jwtTokenProvider.createRefreshToken(username);
        UserAccount account = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("username = " + username));
//        account.setRefreshToken(refreshToken);
        AccessTokenDto tokenDto = new AccessTokenDto(accessToken);
//        tokenDto.setAccessToken(accessToken);
//        tokenDto.setRefreshToken(refreshToken);
        ApiResponse<AccessTokenDto> result = new ApiResponse<>(ResponseCode.USER_LOGIN_SUCCESS, tokenDto);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), result);
    }

}
