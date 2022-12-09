package instagramclone.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Table(name = "Like", indexes = {
        @Index(columnList = "feed"),
        @Index(columnList = "user")
})
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
    private User user;

    protected Like() {};

    private Like(Feed feed, User user) {
        this.feed = feed;
        this.user = user;
    }

    public static Like of(Feed feed, User user) {
        return new Like(feed, user);
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
