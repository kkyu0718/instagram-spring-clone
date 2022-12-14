package instagramclone.dto;

import instagramclone.domain.Feed;
import instagramclone.domain.Image;
import instagramclone.domain.Like;
import instagramclone.dto.request.LikeRequestDto;
import org.hibernate.criterion.LikeExpression;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record FeedDto (
        Long id,
        String content,
        Set<Image> images,
        Set<LikeDto> likesDto,
        Set<CommentDto> commentsDto,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
    ) {

    public static FeedDto of (String content, Set<Image> images) {
        return new FeedDto(null, content, images, null, null, null, null, null, null);
    }

    public static FeedDto from(Feed entity) {
        return new FeedDto(
                entity.getId(),
                entity.getContent(),
                entity.getImages(),
                entity.getLikes()
                        .stream()
                        .map(LikeDto::from)
                        .collect(Collectors.toUnmodifiableSet()),
                entity.getComments()
                        .stream()
                        .map(CommentDto::from)
                        .collect(Collectors.toUnmodifiableSet()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy());
    }
}