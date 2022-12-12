package instagramclone.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(name = "user_account", indexes = {
        @Index(columnList = "email"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter @Column(nullable = false, length = 50) private String email;
    @Setter @Column(nullable = false, length = 200) private String password;
    @Setter @Column(nullable = false, length = 30) private String name;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Like> likes = new HashSet<>();

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    protected UserAccount() {
    }

    private UserAccount(String name, String email, String password, String createdBy) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
    }

    private UserAccount(Long id, String name, String email, String password, String createdBy) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
    }

    public static UserAccount of(String name, String email, String password) {
        return new UserAccount(name, email, password,null);
    }

    public static UserAccount of(String name, String email, String password, String createdBy) {
        return new UserAccount(name, email, password, createdBy);
    }

    public static UserAccount of(Long id, String name, String email, String password, String createdBy) {
        return new UserAccount(id, name, email, password, createdBy);
    }

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
}