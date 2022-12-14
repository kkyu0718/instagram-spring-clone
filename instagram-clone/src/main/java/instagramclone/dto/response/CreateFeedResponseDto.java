package instagramclone.dto.response;

import instagramclone.domain.Image;
import instagramclone.dto.FeedDto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record CreateFeedResponseDto(
        Long id,
        String content,
        Set<String> imageUrls,
        LocalDateTime createdAt,
        String createdBy
) {
    public static CreateFeedResponseDto of (Long id, String content, Set<String> imageUrls) {
        return new CreateFeedResponseDto(id, content, imageUrls, null, null);
    }

    public static CreateFeedResponseDto from(FeedDto dto) {
        return new CreateFeedResponseDto(
                dto.id(),
                dto.content(),
                dto.images()
                        .stream()
                        .map(Image::getImageUrl)
                        .collect(Collectors.toUnmodifiableSet()),
                dto.createdAt(),
                dto.createdBy()
        );
    }
}
