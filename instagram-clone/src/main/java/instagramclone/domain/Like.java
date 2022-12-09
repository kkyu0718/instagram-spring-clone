package instagramclone.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Table(name = "Like", indexes = {
        @Index(columnList = "feed"),
        @Index(columnList = "user")
})
@EntityListeners(AuditingField.class)
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feedId")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
