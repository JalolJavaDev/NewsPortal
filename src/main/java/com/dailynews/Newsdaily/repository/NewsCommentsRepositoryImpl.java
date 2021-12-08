package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.NewsComments;
import com.dailynews.Newsdaily.domen.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class NewsCommentsRepositoryImpl implements NewsCommentsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public NewsComments saveComment(Long newsId, Long userId, String comment) {
        NewsComments newsComments = new NewsComments();
        News news = new News();
        User user = new User();
        news.setNewsId(newsId);
        user.setId(userId);
        newsComments.setComment(comment);
        newsComments.setCreatedTime(new java.util.Date());
        newsComments.setNews(news);
       // newsComments.setUser(user);
        entityManager.persist(newsComments);
        return newsComments;

    }

    @Override
    public boolean updateComment(Long newsId, Long userId, Long commentId) {
        return false;
    }

    @Override
    public List<NewsComments> showAllCommentsByNewsId(long newsId) {
        Query query = entityManager.createQuery("select u from NewsComments u where u.news.newsId=:newsId", NewsComments.class);
        query.setParameter("newsId", newsId);
        List<NewsComments> newsComments = query.getResultList();
        return newsComments;
    }

    @Override
    public boolean deleteComment(Long newsId, Long userId, Long commentId) {
        return false;
    }
}