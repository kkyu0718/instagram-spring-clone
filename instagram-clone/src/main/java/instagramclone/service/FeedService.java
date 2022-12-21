package instagramclone.service;

import instagramclone.domain.Feed;
import instagramclone.domain.Image;
import instagramclone.dto.FeedDto;
import instagramclone.exception.CustomException;
import instagramclone.exception.ErrorCode;
import instagramclone.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class FeedService {

    private final FeedRepository feedRepository;

    @Transactional
    public FeedDto createFeed(String content, Set<String> images) {
//        Set<Image> imageSet = images
//                .stream()
//                .map(Image::of)
//                .collect(Collectors.toUnmodifiableSet());
        //        Feed feed = Feed.of(content, imageSet);
        Feed feed = Feed.of(content);
        Feed savedFeed = feedRepository.save(feed);

        return FeedDto.from(savedFeed);
    }

    @Transactional
    public Optional<FeedDto> getFeed(Long id) {
        Optional<FeedDto> feed = feedRepository.findById(id).map(FeedDto::from);
        return feed;
    }
}
