package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.NewsComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public interface NewsCommentsRepository extends JpaRepository<NewsComments, Long> {
   // NewsComments saveComment(Long newsId, Long userId, NewsComments comment);
    //NewsComments updateComment(Long newsId, Long userId, Long commentId, NewsComments comment);
  @Query("select n from NewsComments n where n.news.newsId = :newsId")
    List<NewsComments> showAllCommentsByNewsId(@Param("newsId") long newsId);
    @Query("select n from NewsComments n where n.commentId = ?1 and n.news.newsId = ?2")
    Optional<NewsComments> findByCommentIdAndNews(Long id, Long newsId);
    void deleteById(long id);
   // void deleteComment(Optional<Long> newsId, Optional<Long> userId,Optional<Long> commentId);
}