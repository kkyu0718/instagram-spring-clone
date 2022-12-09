package instagramclone.domain;

import jakarta.persistence.*;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;
import java.util.Set;

@Table(name = "Image", indexes = {
        @Index(columnList = "feed")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String imageUrl;

    @ManyToOne()
    private Feed feed;

    protected Image() {}

    private Image(String imageUrl, Feed feed) {
        this.imageUrl = imageUrl;
        this.feed = feed;
    }

    public static Image of(String imageUrl, Feed feed) {
        return new Image(imageUrl, feed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id.equals(image.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
