package instagramclone.controller;

import instagramclone.dto.LikeDto;
import instagramclone.dto.request.LikeRequestDto;
import instagramclone.dto.response.ApiResponse;
import instagramclone.dto.response.LikeResponseDto;
import instagramclone.dto.response.ResponseCode;
import instagramclone.exception.CustomException;
import instagramclone.exception.ErrorCode;
import instagramclone.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {
    private final LikeService likeService;

    @PostMapping()
    public ResponseEntity<ApiResponse<LikeResponseDto>> createLike(@RequestBody LikeRequestDto request, @RequestHeader(value = "userId") Long userId) {
        LikeDto likeDto = likeService.createLike(request.feedId(), userId);

        if(likeDto == null) {
            throw new CustomException(ErrorCode.LIKE_NOT_CREATED);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(ResponseCode.LIKE_CREATED, LikeResponseDto.from(likeDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLike(@PathVariable Long id) {
        try {
            likeService.deleteLike(id);
        } catch(RuntimeException error) {
            throw new CustomException(ErrorCode.LIKE_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.LIKE_DELETED));
    }
}
