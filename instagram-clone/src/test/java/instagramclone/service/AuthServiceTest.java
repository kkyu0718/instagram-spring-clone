package instagramclone.service;

import instagramclone.domain.UserAccount;
import instagramclone.dto.UserAccountDto;
import instagramclone.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 회원")
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AuthService sut;

    @DisplayName("회원 가입 성공")
    @Test
    void 회원가입_성공() {
        //given
        UserAccount userAccount = UserAccount.of("kyuwon", "email@domain.com", "password");
        UserAccount savedUserAccount = UserAccount.of(1L, "kyuwon", "email@domain.com", "password", "kyuwon");
//        given(mockedUserRepository.save(any())).willReturn(savedUserAccount); // any 로 고치니 해결 완료 -> 추후 해결 필요
        given(userRepository.save(userAccount)).willReturn(savedUserAccount);

        //when
        UserAccountDto result = sut.saveUser(userAccount.getName(), userAccount.getEmail(), userAccount.getPassword());

        //then
        assertThat(result)
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("email", userAccount.getEmail())
                .hasFieldOrProperty("password") // 암호화를 할 것이기 때문에 value 검사는 제외
                .hasFieldOrPropertyWithValue("name", userAccount.getName());
        
        then(userRepository).should().save(userAccount);
    }
}