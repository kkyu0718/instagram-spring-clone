package instagramclone.service;

import instagramclone.domain.Feed;
import instagramclone.domain.Image;
import instagramclone.dto.FeedDto;
import instagramclone.repository.FeedRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("비즈니스 로직 - 피드")
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class FeedServiceTest {
    @Mock private FeedRepository feedRepository;
    @InjectMocks private FeedService sut;

    @DisplayName("피드 생성 성공")
    @Test
    void givenFeedInfo_whenCreatingFeed_thenReturnSavedFeed() {
        // given
        Image image1 = Image.of("image1@url.com");
        Image image2 = Image.of("image2@url.com");
        Feed feed = Feed.of("내용 입니다", Set.of(image1, image2));
        Feed savedFeed = Feed.of(1L, "내용입니다", Set.of(image1, image2));
        given(feedRepository.save(any())).willReturn(savedFeed); // feed domain의 유니크 필드 문제로 any() 로 대체합니다.

        // when
        FeedDto result = sut.createFeed(feed.getContent(), feed.getImages());

        // then
        assertThat(result)
                .hasFieldOrPropertyWithValue("content", "내용입니다")
                .hasFieldOrPropertyWithValue("images", Set.of(image1, image2));    }
}