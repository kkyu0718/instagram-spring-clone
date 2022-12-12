package instagramclone.dto;

import instagramclone.domain.Comment;
import instagramclone.domain.Feed;
import instagramclone.domain.Image;
import instagramclone.domain.Like;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record FeedDto (
        Long id,
        String content,
        Set<Image> imagesDto,
        Set<Like> likesDto,
        Set<Comment> commentsDto,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
    ) {

    public static FeedDto of (String content, Set<Image> imagesDto) {
        return new FeedDto(null, content, imagesDto, null, null, null, null, null, null);
    }

    public static FeedDto from(Feed entity) {
        return new FeedDto(
                entity.getId(),
                entity.getContent(),
                entity.getImages()
                        .stream()
                        .map(Image::from)
                        .collect(Collectors.toUnmodifiableSet()),
                entity.getLikes()
                        .stream()
                        .map(Like::from)
                        .collect(Collectors.toUnmodifiableSet()),
                entity.getComments()
                        .stream()
                        .map(Comment::from)
                        .collect(Collectors.toUnmodifiableSet()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy());
    }
}