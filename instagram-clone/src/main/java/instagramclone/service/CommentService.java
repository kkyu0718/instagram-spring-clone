package instagramclone.service;

import instagramclone.domain.Comment;
import instagramclone.domain.Feed;
import instagramclone.domain.UserAccount;
import instagramclone.dto.CommentDto;
import instagramclone.repository.CommentRepository;
import instagramclone.repository.FeedRepository;
import instagramclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;

    @Transactional
    public CommentDto createComment(String content, Long feedId, Long userId) {
        Feed feed = feedRepository.getById(feedId);
        UserAccount user = userRepository.getById(userId);

        Comment comment = Comment.of(feed, user, null, content);
        Comment savedComment = commentRepository.save(comment);
        return CommentDto.from(savedComment);
    }

    @Transactional
    public CommentDto createChildComment(String content, Long feedId, Long parentCommentId, Long userId) {
        Feed feed = feedRepository.getById(feedId);
        UserAccount user = userRepository.getById(userId);
        Comment comment = Comment.of(feed, user, parentCommentId, content);
        Comment savedComment = commentRepository.save(comment);
        return CommentDto.from(savedComment);
    }

    public Optional<CommentDto> getComment(Long id) {
        Optional<CommentDto> comment = commentRepository.findById(id).map(CommentDto::from);
        return comment;
    }
}
