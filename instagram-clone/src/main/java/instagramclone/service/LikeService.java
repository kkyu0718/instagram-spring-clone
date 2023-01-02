package instagramclone.service;

import instagramclone.domain.Comment;
import instagramclone.domain.Feed;
import instagramclone.domain.Like;
import instagramclone.domain.UserAccount;
import instagramclone.dto.LikeDto;
import instagramclone.repository.FeedRepository;
import instagramclone.repository.LikeRepository;
import instagramclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;

    @Transactional
    public LikeDto createLike(Long feedId, Long userId) {
        UserAccount user = userRepository.getById(userId);
        Feed feed = feedRepository.getById(feedId);

        Like like = Like.of(feed, user);
        Like savedLike = likeRepository.save(like);
        return LikeDto.from(savedLike);
    }

    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
        return;
    }
}
