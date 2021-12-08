package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.Users;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Repository
@Transactional
public class NewsRepositoryImpl implements NewsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<News> findAllNews() {
        TypedQuery<News> newsList = entityManager.createQuery("select u from News u where u.isActive=true", News.class);
        List<News> news = newsList.getResultList();
        return news;
    }

    @Override
    public News findById(long id) {
        Query news = entityManager.createQuery("select u from News u where u.newsId=:id and u.isActive=true", News.class);
        news.setParameter("id", id);
        News n = (News) news.getSingleResult();
        return n;
    }

    @Override
    public News findByTitl(String title) {
        return newsRepository.fi;
    }




    @Override
    public void deleteById(long id) {
        News news = entityManager.find(News.class, id);
        if (news != null) {
            entityManager.remove(news);
        } else {
            System.out.println("news is not exists...!");
        }
    }

    @Override
    public List<Users> showAllUsers() {
        TypedQuery<Users> newsList = entityManager.createQuery("Select u from users u", Users.class);
        List<Users> users = newsList.getResultList();
        return users;
    }

    @Override
    public List<News> findAll() {
        return null;
    }

    @Override
    public List<News> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<News> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<News> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(News entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends News> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends News> S save(S entity) {
        return null;
    }

    @Override
    public <S extends News> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<News> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends News> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends News> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<News> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public News getOne(Long aLong) {
        return null;
    }

    @Override
    public News getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends News> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends News> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends News> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends News> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends News> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends News> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends News, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}