package instagramclone.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Table(name = "Tag", indexes = {
        @Index(columnList = "feed"),
        @Index(columnList = "user")
})
@Entity
public class Tag extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feedId")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    protected Tag() {};

    private Tag(Feed feed, User user) {
        this.feed = feed;
        this.user = user;
    }

    public static Tag of(Feed feed, User user) {
        return new Tag(feed, user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id.equals(tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
