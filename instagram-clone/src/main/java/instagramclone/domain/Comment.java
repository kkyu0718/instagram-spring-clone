package instagramclone.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(name = "Comment", indexes = {
        @Index(columnList = "feed"),
        @Index(columnList = "user")
})
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feedId")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Setter
    @Column(updatable = false)
    private Long parentCommentId; // 부모 댓글 ID

    @ToString.Exclude
    @OrderBy("createdAt ASC")
    @OneToMany(mappedBy = "parentCommentId", cascade = CascadeType.ALL)
    private Set<Comment> childComments = new LinkedHashSet<>();

    @Setter @Column(nullable = false, length = 500)
    private String content;

    protected Comment() {}

    private Comment(Feed feed, User user, Long parentCommentId, String content) {
        this.feed = feed;
        this.user = user;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }

    public static Comment of(Feed feed, User user, Long parentCommentId, String content) {
        return new Comment(feed, user, null, content);
    }

    public void addChildComment(Comment child) {
        child.setParentCommentId(this.getId());
        this.getChildComments().add(child);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
