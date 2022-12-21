package instagramclone.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Table(name = "Image")
@Entity
public class Image extends AuditingField{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String imageUrl;

    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name = "feed_id")
    private Feed feed;

    protected Image() {}

    private Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static Image of(String imageUrl) {
        return new Image(imageUrl);
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
