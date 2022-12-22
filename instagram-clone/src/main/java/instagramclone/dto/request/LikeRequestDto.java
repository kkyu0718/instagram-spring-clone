package instagramclone.dto.request;

public record LikeRequestDto(Long feedId)
{
    public static LikeRequestDto of(Long feedId){
        return new LikeRequestDto(feedId);
    }
}