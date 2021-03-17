package com.hanna.rss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "rssEmails")
public class RssEmails {
    @Id
    String id;

    @Field("email")
    String email;

    @Field("rssUrls")
    List<String> rssUrls;
}
