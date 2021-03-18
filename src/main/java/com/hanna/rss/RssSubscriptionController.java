package com.hanna.rss;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@CrossOrigin
public class RssSubscriptionController {

    private final RssMailService rssMailService;
    private final RssEmailsRepository rssEmailsRepository;

    @PostMapping("/rss")
    public ResponseEntity saveRssUrls(
        @RequestBody RssEmails emailRssUrls
    ) {
        rssEmailsRepository.deleteAllByEmail(emailRssUrls.getEmail());
        rssEmailsRepository.insert(emailRssUrls);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/preview", produces = MediaType.TEXT_HTML_VALUE)
    public String prepareRssMailPreview(String email) {
        return rssMailService.prepareRssMailHtmlPreview(email);
    }

    @GetMapping("/send")
    public void sendRssEmail(String email) {
        rssMailService.sendRssMail(email);
    }
}
