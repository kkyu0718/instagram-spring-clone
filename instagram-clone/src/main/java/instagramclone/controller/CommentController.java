package instagramclone.controller;

import instagramclone.dto.CommentDto;
import instagramclone.dto.request.CreateCommentRequestDto;
import instagramclone.dto.response.ApiResponse;
import instagramclone.dto.response.CommentResponseDto;
import instagramclone.dto.response.ResponseCode;
import instagramclone.exception.CustomException;
import instagramclone.exception.ErrorCode;
import instagramclone.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse<CommentResponseDto>> createComment(@RequestBody CreateCommentRequestDto request, @RequestHeader(value = "userId") Long userId) {
        if(request.parentCommentId() != null){
            CommentDto commentDto = commentService.createChildComment(request.content(), request.feedId(), request.parentCommentId(), userId);
            if(commentDto == null) {
                throw new CustomException(ErrorCode.COMMENT_NOT_CREATED);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(ResponseCode.COMMENT_CREATED, CommentResponseDto.from(commentDto)));
        }

        CommentDto commentDto =  commentService.createComment(request.content(), request.feedId(), userId);
        if(commentDto == null) {
            throw new CustomException(ErrorCode.COMMENT_NOT_CREATED);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(ResponseCode.COMMENT_CREATED, CommentResponseDto.from(commentDto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CommentResponseDto>> getComment(@PathVariable Long id) {
        CommentDto commentDto = commentService.getComment(id)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse<>(ResponseCode.COMMENT_FOUND, CommentResponseDto.from(commentDto)));
    }
}
