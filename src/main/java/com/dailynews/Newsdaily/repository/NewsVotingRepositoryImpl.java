package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.NewsVoting;
import com.dailynews.Newsdaily.domen.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class NewsVotingRepositoryImpl implements NewsVotingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean upVoteCounting(Long userId, Long newsId) {
        User user = new User(userId);
        News news = new News(newsId);
        Query query = entityManager.createQuery("select u from NewsVoting u where u.news.newsId=:newsId and u.user.id=:userId", NewsVoting.class);
        query.setParameter("userId", userId);
        query.setParameter("newsId", newsId);
        try {
            NewsVoting newsVoting = (NewsVoting) query.getSingleResult();
            if (newsVoting.getUpVote() == true) {
                newsVoting.setUpVote(false);
                entityManager.persist(newsVoting);
            } else {
                newsVoting.setUpVote(true);
                entityManager.persist(newsVoting);
            }
        } catch (NoResultException e) {
            NewsVoting newsVoting = new NewsVoting(true, false, news, user);
            entityManager.persist(newsVoting);
        }
        return false;
    }

    @Override
    public boolean downVoteCounting(Long userId, Long newsId) {
        Query query = entityManager.createQuery(
                "select u from NewsVoting u where u.news.newsId=:newsId and u.user.id=:userId", NewsVoting.class);
        query.setParameter("userId", userId);
        query.setParameter("newsId", newsId);
        try {
            NewsVoting newsVoting = (NewsVoting) query.getSingleResult();
            if (newsVoting.getDownVote() == true) {
                newsVoting.setDownVote(false);
                entityManager.persist(newsVoting);
            } else {
                newsVoting.setDownVote(true);
                entityManager.persist(newsVoting);
            }
        } catch (NoResultException e) {
            User user = new User(userId);
            News news = new News(newsId);
            NewsVoting newsVoting = new NewsVoting(false, true, news, user);
            entityManager.persist(newsVoting);
        }
        return false;
    }

    @Override
    public Long voteCounter() {
        return null;
    }
}