package instagramclone.dto.response;

import instagramclone.domain.Image;
import instagramclone.dto.FeedDto;

import java.time.LocalDateTime;
import java.util.Set;

public record CreateFeedResponse(
        Long id,
        String content,
        Set<Image> images,
        LocalDateTime createdAt,
        String createdBy
) {
    public static CreateFeedResponse of (Long id, String content, Set<Image> images) {
        return new CreateFeedResponse(id, content, images, null, null);
    }

    public static CreateFeedResponse from(FeedDto dto) {
        return new CreateFeedResponse(
                dto.id(),
                dto.content(),
                dto.imagesDto()
                        .stream()
                        .map(Image::imageUrl),
                dto.createdAt(),
                dto.createdBy()
        );
    }
}
