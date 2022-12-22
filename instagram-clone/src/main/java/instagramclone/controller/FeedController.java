package instagramclone.controller;

import instagramclone.dto.FeedDto;
import instagramclone.dto.request.CreateFeedRequestDto;
import instagramclone.dto.response.FeedResponseDto;
import instagramclone.exception.CustomException;
import instagramclone.exception.ErrorCode;
import instagramclone.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService feedService;

    @PostMapping("/")
    public ResponseEntity<FeedResponseDto> createFeed(@RequestBody CreateFeedRequestDto request) {
        FeedDto feed = feedService.createFeed(request.content(), request.images());
        if(feed == null) {
            throw new CustomException(ErrorCode.FEED_NOT_CREATED);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(FeedResponseDto.from(feed));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedResponseDto> getFeed(@PathVariable Long id) {
        FeedDto feed = feedService.getFeed(id).orElseThrow(() -> new CustomException(ErrorCode.FEED_NOT_FOUND));
        return ResponseEntity.status(HttpStatus.FOUND).body(FeedResponseDto.from(feed));
    }
}
