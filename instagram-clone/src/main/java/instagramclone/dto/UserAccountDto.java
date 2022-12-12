package instagramclone.dto;


import instagramclone.domain.Comment;
import instagramclone.domain.Like;
import instagramclone.domain.UserAccount;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


public record UserAccountDto(
                             String name,
                             String email,
                             String password,
                             Set<Like> likes,
                             Set<Comment> comments,
                             LocalDateTime createdAt,
                             String createdBy,
                             LocalDateTime modifiedAt,
                             String modifiedBy)  {
    public static UserAccountDto of(String email, String password, String name){
        return new UserAccountDto(email, password, name, null, null, null, null, null, null);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getLikes(),
//                        .stream()
//                        .map(LikesDto::from)
//                        .collect(Collectors.toUnmodifiableSet()),
                entity.getComments(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
                );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                email,
                password,
                name
        );
    }
}
