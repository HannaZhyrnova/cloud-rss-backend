package com.hanna.rss;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class RssMailPreviewService {

    private static final String HTML_EMAIL_TEMPLATE_RSS = "<div>\n" +
        "            <h2>{title}</h2>\n" +
        "            <p>{description}</p>\n" +
        "            <a href=\"{url}\">Full article</a>\n" +
        "        </div>";

    private static final String HTML_EMAIL_TEMPLATE_BODY = "<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
            "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
            "    <title>Email</title>\n" +
            "</head>\n" +
            "<body>\n" +
                "<div>\n" +
                "{body}\n" +
                "</div>\n" +
            "</body>\n" +
        "</html>";

    private final RssEmailsRepository rssEmailsRepository;

    public String prepareRssMailHtmlPreview(String email) {
        RssEmails rssEmails = rssEmailsRepository.findRssEmailsByEmail(email);

        String blocks = rssEmails.rssUrls.stream()
            .map(this::fetchRecentFeed)
            .flatMap(feed -> (Stream<SyndEntry>)feed.getEntries().stream())
            .map(this::toHTMLBlock)
            .collect(Collectors.joining("\n"));

        String finalHtmlEmail = HTML_EMAIL_TEMPLATE_BODY.replace("{body}", blocks);
        return finalHtmlEmail;
    }

    @SneakyThrows
    private SyndFeed fetchRecentFeed(String url) {
        URL feedSource = new URL(url);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));
        return feed;
    }

    private String toHTMLBlock(SyndEntry entry) {
        return HTML_EMAIL_TEMPLATE_RSS
            .replace("{title}", entry.getTitle())
            .replace("{description}", entry.getDescription().getValue())
            .replace("{url}", entry.getLink());
    }
}
