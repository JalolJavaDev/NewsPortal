package com.dailynews.Newsdaily.repository;
public interface NewsVotingRepository {

    boolean upVoteCounting(Long userId, Long newsId);

    boolean downVoteCounting(Long userId, Long newsId);

    Long voteCounter();
}