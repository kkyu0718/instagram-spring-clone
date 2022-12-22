package instagramclone.controller;

import instagramclone.domain.UserAccount;
import instagramclone.dto.UserAccountDto;
import instagramclone.dto.request.LoginRequestDto;
import instagramclone.dto.request.SignInRequestDto;
import instagramclone.dto.response.ApiResponse;
import instagramclone.dto.response.ResponseCode;
import instagramclone.dto.response.UserAccountResponseDto;
import instagramclone.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserAccountResponseDto>> signIn (@RequestBody SignInRequestDto request) {
        UserAccountDto userDto = authService.saveUser(request.name(), request.email(), request.password());
        UserAccountResponseDto user = UserAccountResponseDto.from(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(ResponseCode.USER_CREATED, user));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserAccountResponseDto>> login (@RequestBody LoginRequestDto request) {
        UserAccountDto user = authService.loginUser(request.email(), request.password());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(ResponseCode.USER_LOGIN_SUCCESS, UserAccountResponseDto.from(user)));
    }
}
