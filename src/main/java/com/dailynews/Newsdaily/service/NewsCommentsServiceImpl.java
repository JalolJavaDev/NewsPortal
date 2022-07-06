package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.NewsComments;
import com.dailynews.Newsdaily.domen.NewsUserDetails;
import com.dailynews.Newsdaily.domen.User;
import com.dailynews.Newsdaily.exception.BlogapiException;
import com.dailynews.Newsdaily.exception.ResourceNotFoundException;
import com.dailynews.Newsdaily.repository.NewsCommentsRepository;
import com.dailynews.Newsdaily.repository.NewsRepository;
import com.dailynews.Newsdaily.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@Service
public class NewsCommentsServiceImpl implements NewsCommentsService {

private final NewsCommentsRepository newsCommentsRepository;
private final NewsRepository newsRepository;
private  final UserRepository userRepository;
    private static final String COMMENT_STR = "NewsComments";
    private static final String ID_STR = "commentId";
    private static final String POST_STR = "News";
    private static final String COMMENT_DOES_NOT_BELONG_TO_POST = "Comment does not belong to post";
    public NewsCommentsServiceImpl(NewsCommentsRepository newsCommentsRepository, NewsRepository newsRepository, UserRepository userRepository) {

        this.newsCommentsRepository = newsCommentsRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<NewsComments> showAllNewsCommentsByNewsId(long newsId) {
        List<NewsComments>newsComments=newsCommentsRepository.showAllCommentsByNewsId(newsId);
        return newsComments;
    }


    @Override
    public NewsComments addComment(NewsComments comment, Long newsId, Long userId) {
        NewsComments newsComments = new NewsComments();
        News news = new News();
        User user = new User();
        news.setNewsId(newsId);
        user.setId(userId);

        newsComments.setComment(comment.getComment());
        newsComments.setActive(true);
        newsComments.setCreatedTime(new java.util.Date());
        newsComments.setNews(news);
        newsComments.setUser(user);

        return newsCommentsRepository.save(newsComments);
    }

    @Override
    public NewsComments getComment(Long newsId, Long commentId) {
        return null;
    }

    @Override
        public NewsComments updateComment(Long newsId, Long commentId, NewsComments newsComment, Long userId) {
            User user = new User();
            News news=new News();
           news.setNewsId(newsId);
            user.setId(userId);
            NewsComments comments = new NewsComments();
        comments.setNews(news);
        comments.setUser(user);
        comments.setComment(newsComment.getComment());
        comments.setCommentId(newsComment.getCommentId());
        comments.setUser(newsComment.getUser());
        comments.setNews(newsComment.getNews());
            return newsCommentsRepository.save(comments);
        }





    @Override
    public void delete(long id) {
        newsCommentsRepository.deleteById(id);
    }


/*
    @Override
    public void deleteNewsComment(Optional<Long> newsId, Optional<Long> userId, Optional<Long> commentId) {
        newsCommentsRepository.deleteComment(newsId, userId, commentId);
    }
*/

/*
    @Override
    public boolean deleteNewsComment(Long newsId, Long userId, Long commentId) {
        return false;
    }*/
}
