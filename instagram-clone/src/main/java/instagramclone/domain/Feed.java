package instagramclone.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(name = "Feed", indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Feed extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String content;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feed")
    private Set<Image> images = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private Set<Like> likes = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private Set<Tag> tags = new HashSet<>();

    protected Feed() {}

    private Feed(String content) {
        this.content = content;
    }

    public static Feed of(String content) {
        return new Feed(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feed feed = (Feed) o;
        return id.equals(feed.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
