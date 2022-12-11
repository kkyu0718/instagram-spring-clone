package instagramclone.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Table(name = "Likes")
@Entity
public class Like extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feedId")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserAccount userAccount;

    protected Like() {};

    private Like(Feed feed, UserAccount userAccount) {
        this.feed = feed;
        this.userAccount = userAccount;
    }

    public static Like of(Feed feed, UserAccount userAccount) {
        return new Like(feed, userAccount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return id.equals(like.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
