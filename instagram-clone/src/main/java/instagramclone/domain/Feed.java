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

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Image> images = new HashSet<>();

    @Setter
    @ToString.Exclude
    @OneToMany(mappedBy = "feed",cascade = CascadeType.ALL)
    private Set<Like> likes = new HashSet<>();

    @Setter
    @ToString.Exclude
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    protected Feed() {}

    private Feed(String content) {
        this.content = content;
    }

    private Feed(String content, Set<Image> images) {
        this.content = content;
        this.images = images;
    }

    private Feed(Long id, String content, Set<Image> images) {
        this.id = id;
        this.content = content;
        this.images = images;
    }

    public static Feed of(String content) {
        return new Feed(content);
    }

    public static Feed of(String content, Set<Image> images) {
        return new Feed(content, images);
    }

    public static Feed of(Long id, String content, Set<Image> images) {
        return new Feed(id, content, images);
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
