package instagramclone.domain;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Table(name = "Image", indexes = {
        @Index(columnList = "feed")
})
@Entity
public class Image extends AuditingField{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String imageUrl;

    @ToString.Exclude
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
