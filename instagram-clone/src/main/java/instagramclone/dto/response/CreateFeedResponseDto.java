package instagramclone.dto.response;

import instagramclone.domain.Image;
import instagramclone.dto.FeedDto;

import java.time.LocalDateTime;
import java.util.Set;

public record CreateFeedResponseDto(
        Long id,
        String content,
        Set<Image> images,
        LocalDateTime createdAt,
        String createdBy
) {
    public static CreateFeedResponseDto of (Long id, String content, Set<Image> images) {
        return new CreateFeedResponseDto(id, content, images, null, null);
    }

    public static CreateFeedResponseDto from(FeedDto dto) {
        return new CreateFeedResponseDto(
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
