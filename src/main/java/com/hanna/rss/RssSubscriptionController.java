package com.hanna.rss;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class RssSubscriptionController {

    private final RssMailPreviewService rssMailPreviewService;

    @PostMapping("/rss")
    public ResponseEntity saveRssUrls(
        @RequestBody EmailRssUrls emailRssUrls
    ) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/preview", produces = MediaType.TEXT_HTML_VALUE)
    public String prepareRssMailPreview(String email) {
        return rssMailPreviewService.prepareRssMailHtmlPreview(email);
    }
}
