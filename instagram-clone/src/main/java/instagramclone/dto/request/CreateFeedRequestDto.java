package instagramclone.dto.request;

import instagramclone.domain.Image;

import java.util.Set;

public record CreateFeedRequestDto(
                    String content,
                    Set<Image> images
                    ) {

    public static CreateFeedRequestDto of (String content, Set<Image> images) {
        return new CreateFeedRequestDto(content, images);
    }
}
