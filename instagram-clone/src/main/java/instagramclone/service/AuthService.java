package instagramclone.service;

import instagramclone.domain.UserAccount;
import instagramclone.dto.UserAccountDto;
import instagramclone.dto.response.AccessTokenDto;
import instagramclone.exception.CustomException;
import instagramclone.exception.ErrorCode;
import instagramclone.model.Authority;
import instagramclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public UserAccountDto saveUser(String name, String email, String password) {
        UserAccount userAccount = UserAccount.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .authorities(Set.of(Authority.USER))
                .build();
        validateDuplicateEmail(email);
        UserAccount save = userRepository.save(userAccount);
        return UserAccountDto.from(save);
    }

    public AccessTokenDto loginUser(String email, String password) {
        UserAccount user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

//        boolean match = passwordEncoder.matches(password, user.getPassword());
//        if(!match) {
//            throw new CustomException(ErrorCode.LOGIN_FAILURE);
//        }
//        String token = JwtUtils.createToken(user);
        String token = "예시";
        return AccessTokenDto.of(token);
    }

    private void validateDuplicateEmail(String email) {
        Optional<UserAccount> user = userRepository.findByEmail(email);
        if(!user.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
    }
}
