package instagramclone.service;

import instagramclone.domain.Feed;
import instagramclone.domain.Image;
import instagramclone.dto.FeedDto;
import instagramclone.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class FeedService {

    private final FeedRepository feedRepository;

    @Transactional
    public FeedDto createFeed(String content, Set<Image> images) {
        Feed feed = Feed.of(content, images);
        Feed savedFeed = feedRepository.save(feed);
        return FeedDto.from(savedFeed);
    }
}
