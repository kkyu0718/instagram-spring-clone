package instagramclone.domain;

import instagramclone.model.Authority;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@ToString
@Table(name = "user_account", indexes = {
        @Index(columnList = "email"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserAccount extends AuditingField implements UserDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter @Column(nullable = false, length = 50) private String email;
    @Setter @Column(nullable = false, length = 200) private String password;
    @Setter @Column(nullable = false, length = 30) private String name;
    @ElementCollection(fetch = FetchType.EAGER) @Column(nullable = false, length = 50) @Enumerated(EnumType.STRING) private Set<Authority> authorities;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Like> likes = new HashSet<>();

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    // 원래대로라면 id만을 비교하겠지만 service test code 상의 문제 이슈로 id가 아닌 unique key인 이메일을 비교합니다.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(r -> new SimpleGrantedAuthority("ROLE_"+ r.name())).collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
