package instagramclone.dto.request;

import instagramclone.dto.UserAccountDto;

import java.io.Serializable;

public record LikeRequestDto(Long id, Long feedId, UserAccountDto userAccountDto)
{
    public static LikeRequestDto of(Long id, Long feedId, UserAccountDto userAccountDto){
        return new LikeRequestDto(id, feedId, userAccountDto);
    }
}