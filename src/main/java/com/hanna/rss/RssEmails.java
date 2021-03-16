package com.hanna.rss;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class RssEmails {
    @Id
    String id;

    @Field("email")
    String email;

    @Field("rssUrls")
    List<String> rssUrls;
}
