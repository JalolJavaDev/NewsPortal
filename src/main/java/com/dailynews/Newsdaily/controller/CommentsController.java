package com.dailynews.Newsdaily.controller;

import com.dailynews.Newsdaily.domen.*;
import com.dailynews.Newsdaily.exception.ResourceNotFoundException;
import com.dailynews.Newsdaily.repository.NewsCommentsRepository;
import com.dailynews.Newsdaily.repository.NewsRepository;
import com.dailynews.Newsdaily.service.NewsCommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentsController {
    private final NewsCommentsService newsCommentsService;

    public CommentsController(NewsCommentsService newsCommentsService, NewsCommentsRepository newsCommentsRepository, NewsRepository newsRepository) {
        this.newsCommentsService = newsCommentsService;
        this.newsCommentsRepository = newsCommentsRepository;
        this.newsRepository = newsRepository;
    }

    /*
        @GetMapping("/newsImage")
        public ResponseEntity<?> getAll(Pageable pageable){
            Page<NewsImage> newsImage=newsImageServices.findAllImage(pageable);
            return ResponseEntity.ok(newsImage);
        }
        @GetMapping("/newsImage/news/{newsId}")
        public ResponseEntity<List<NewsImage>> getByNewsId(@PathVariable Long newsId){
            List<NewsImage> newsImage=  newsImageServices.findByNews(newsId);
            return ResponseEntity.ok(newsImage);
        }
        @GetMapping("/newsImage/{id}")
        public ResponseEntity<NewsImage> getOne(@PathVariable Long id){
            NewsImage newsImage=newsImageServices.findOne(id);
            return ResponseEntity.ok(newsImage);
        }*/
    private final NewsCommentsRepository newsCommentsRepository;

    @PostMapping("/{userId}/newsComments/{newsId}")
    public ResponseEntity<?> postNewsComments(@Valid @RequestBody NewsComments comment,
                                              @PathVariable(value = "newsId") long newsId,
                                              @PathVariable(value = "userId") long userId) {

        String userName = null;
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            NewsUserDetails s = (NewsUserDetails) ((org.springframework.security.core.Authentication) loggedInUser).getPrincipal();
            userId = s.getId();
            userName = s.getUsername();
        } catch (Exception e) {
        }
        if (loggedInUser.getPrincipal().equals("anonymousUser")) {
        } else {
            newsCommentsService.addComment(comment, newsId, userId);
            User user = new User();
            user.setUsername(userName);
            NewsComments newsComments = new NewsComments();
            newsComments.setUser(user);
            // newsComments.setCommentId(comment.getCommentId());
            newsComments.setComment(comment.getComment());
            return new ResponseEntity(newsComments, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
  //  @PutMapping("/posts/{postId}/comments/{commentId}")
    private final NewsRepository newsRepository;
    @PutMapping("/newsComments/{newsId}/{commentId}")
    public NewsComments updateComment(@PathVariable (value = "newsId") Long newsId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody NewsComments commentRequest) {
        if(!newsRepository.existsById(newsId)) {
            throw new ResourceNotFoundException("newsId " + newsId + " not found");
        }

        return newsCommentsRepository.findById(commentId).map(comment -> {
          //  comment.setText(commentRequest.getText());
            comment.setCommentId(commentRequest.getCommentId());
            comment.setComment(commentRequest.getComment());
            comment.setUpdatedTime(new java.util.Date());
            // comment.setNews();
            return newsCommentsRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }
    @DeleteMapping("/news/{newsId}/newsComments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "newsId") Long newsId,
                                           @PathVariable (value = "commentId") Long commentId) {
        return newsCommentsRepository.findByCommentIdAndNews(commentId, newsId).map(comment -> {
            newsCommentsRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + newsId));
    }


    @PostMapping("/showComments")
    public ResponseEntity<List<NewsComments>> showComments(@RequestParam("newsId") long newsId) {
        List<NewsComments> category = newsCommentsService.showAllNewsCommentsByNewsId(newsId);
        return ResponseEntity.ok(category);
    }
    /*
    @DeleteMapping("{userId}/newsComments/{newsId}/{commentId}")
    public ResponseEntity<NewsComments> delete(@PathVariable(value = "newsId") Optional<Long> newsId,
                                               @PathVariable(value = "userId") Optional<Long> userId,
                                               @PathVariable(value = "commentId") Optional<Long> commentId){

        newsCommentsService.deleteNewsComment(newsId,userId,commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
*/

}