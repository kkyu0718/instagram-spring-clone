package instagramclone.config.jwt;

import instagramclone.domain.UserAccount;
import instagramclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserAccount account = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("username = " + username));

        return User.withUsername(username)
                .password(account.getPassword())
                .authorities(AuthorityUtils.NO_AUTHORITIES)
                .build();
    }

}