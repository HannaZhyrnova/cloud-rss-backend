package com.hanna.rss;

import lombok.Data;

import java.util.List;

@Data
public class EmailRssUrls {
    private final String email;
    private final List<String> rssUrls;
}
