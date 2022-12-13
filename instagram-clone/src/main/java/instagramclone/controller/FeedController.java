package instagramclone.controller;

import instagramclone.dto.FeedDto;
import instagramclone.dto.request.CreateFeedRequestDto;
import instagramclone.dto.response.CreateFeedResponseDto;
import instagramclone.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService feedService;

    @PostMapping("/")
    public ResponseEntity<CreateFeedResponseDto> createFeed(@RequestBody CreateFeedRequestDto request) {
        FeedDto feed = feedService.createFeed(request.content(), request.images());
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateFeedResponseDto.from(feed));
    }

}
