package instagramclone.dto.request;

import instagramclone.domain.Image;

import java.util.Set;

public record CreateFeedRequest (
                    String content,
                    Set<Image> images
                    ) {

    public static CreateFeedRequest of (String content, Set<Image> images) {
        return new CreateFeedRequest(content, images);
    }
}
