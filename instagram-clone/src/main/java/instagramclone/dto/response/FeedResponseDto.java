package instagramclone.dto.response;

import instagramclone.domain.Image;
import instagramclone.dto.FeedDto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record FeedResponseDto(
        Long id,
        String content,
        Set<String> imageUrls,
        LocalDateTime createdAt,
        String createdBy
) {
    public static FeedResponseDto of (Long id, String content, Set<String> imageUrls) {
        return new FeedResponseDto(id, content, imageUrls, null, null);
    }

    public static FeedResponseDto from(FeedDto dto) {
        return new FeedResponseDto(
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
