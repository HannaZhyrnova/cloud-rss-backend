package com.hanna.rss;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RssEmailsRepository {//extends MongoRepository<RssEmails, String>, QuerydslPredicateExecutor<RssEmails> {
    @Query("{ 'email' : ?0 }")
    RssEmails findRssEmailsByEmail(String email);
}