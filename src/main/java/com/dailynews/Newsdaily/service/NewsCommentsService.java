package com.dailynews.Newsdaily.service;

import com.dailynews.Newsdaily.domen.NewsComments;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Optional;

@Service
public interface NewsCommentsService {
    //NewsComments saveNewsComment(Long newsId, Long userId,NewsComments newsComments);

    List<NewsComments> showAllNewsCommentsByNewsId(long newsId);
    NewsComments addComment(NewsComments newsComment, Long newsId, Long userId);

    NewsComments getComment(Long newsId, Long commentId);

    NewsComments updateComment(Long newsId, Long commentId, NewsComments newsComment, Long userId);

    //ApiResponse deleteComment(Long postId, Long id, UserPrincipal currentUser);
    // boolean updateNewsComment(Long newsId, Long userId, Long commentId);

  // List<NewsComments> showAllNewsCommentsByNewsId(long newsId);

  // void deleteNewsComment(Long newsId, Long userId, Long commentId);
void delete(long id);
  //  void deleteNewsComment(Optional<Long> newsId, Optional<Long> userId, Optional<Long> commentId);
}
