package com.hanna.rss;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RssEmailsRepository extends MongoRepository<RssEmails, String> {
    RssEmails findRssEmailsByEmail(String email);
}