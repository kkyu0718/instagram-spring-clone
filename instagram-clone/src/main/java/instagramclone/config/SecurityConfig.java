package instagramclone.config;

import instagramclone.config.jwt.JwtAuthenticationEntryPoint;
import instagramclone.config.jwt.JwtAuthenticationFilter;
import instagramclone.config.jwt.JwtAuthorizationFilter;
import instagramclone.config.jwt.JwtProperties;
import instagramclone.domain.UserAccount;
import instagramclone.repository.UserRepository;
import instagramclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Security 설정 Config
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // basic authentication
        http.httpBasic().disable(); // basic authentication filter 비활성화
        // csrf
        http.csrf().disable();
        // remember-me
        http.rememberMe().disable();
        // stateless
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // jwt filter
        http.addFilterBefore(
                new JwtAuthenticationFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class
        ).addFilterBefore(
                new JwtAuthorizationFilter(userRepository),
                BasicAuthenticationFilter.class
        );
        // exception
        http.exceptionHandling()
                        .authenticationEntryPoint(authenticationEntryPoint);
        // authorization
        http.authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
//                .antMatchers("/note").hasRole("USER")
//                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/feed/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        // 정적 리소스 spring security 대상에서 제외
//        web.ignoring().antMatchers("/images/**", "/css/**"); // 아래 코드와 같은 코드입니다.
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * UserDetailsService 구현
     *
     * @return UserDetailsService
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            UserAccount user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(username);
            }
            return user;
        };
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}