package instagramclone.service;

import instagramclone.domain.UserAccount;
import instagramclone.dto.UserAccountDto;
import instagramclone.dto.request.SignInRequest;
import instagramclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public UserAccountDto saveUser(String name, String email, String password) {
        UserAccount userAccount = UserAccount.of(name, email, password);
        UserAccount save = userRepository.save(userAccount);
        return UserAccountDto.from(save);
    }
}
