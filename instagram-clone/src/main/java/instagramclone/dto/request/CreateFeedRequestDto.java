package instagramclone.dto.request;

import instagramclone.domain.Image;

import java.util.Set;

public record CreateFeedRequestDto(
                    String content,
                    Set<String> images
                    ) {

    public static CreateFeedRequestDto of (String content, Set<String> images) {
        return new CreateFeedRequestDto(content, images);
    }
}
