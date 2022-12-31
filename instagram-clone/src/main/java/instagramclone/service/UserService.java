package instagramclone.service;

import instagramclone.domain.UserAccount;
import instagramclone.exception.CustomException;
import instagramclone.exception.ErrorCode;
import instagramclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserAccount findByUsername(String username) {
        UserAccount user = userRepository.findByName(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return user;
    }
}
