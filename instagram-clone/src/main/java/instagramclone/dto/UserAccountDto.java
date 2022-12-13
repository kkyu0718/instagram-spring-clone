package instagramclone.dto;


import instagramclone.domain.Comment;
import instagramclone.domain.Like;
import instagramclone.domain.UserAccount;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


public record UserAccountDto(
                            Long id,
                             String name,
                             String email,
                             String password,
                             LocalDateTime createdAt,
                             String createdBy,
                             LocalDateTime modifiedAt,
                             String modifiedBy)  {
    public static UserAccountDto of(Long id, String email, String password, String name){
        return new UserAccountDto(id, email, password, name, null, null, null, null);
    }

    public static UserAccountDto of(String email, String password, String name){
        return new UserAccountDto(null, email, password, name, null, null, null, null);
    }
    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
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
