package edu.java.scrapper.appclients;

import edu.java.scrapper.dto.request.AddLinkRequest;
import edu.java.scrapper.dto.request.RemoveLinkRequest;
import edu.java.scrapper.dto.response.LinkResponse;
import edu.java.scrapper.dto.response.ListLinksResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ScrapperClient {
    @PostMapping("/tg-chat/{id}")
    void registerChat(@PathVariable long id);

    @DeleteMapping("/tg-chat/{id}")
    void deleteChat(@PathVariable long id);

    @GetMapping("/links")
    ListLinksResponse getLinks(@RequestHeader("Tg-Chat-Id") long tgChatId);

    @PostMapping("/links")
    LinkResponse addLink(
        @RequestHeader("Tg-Chat-Id") long tgChatId,
        @RequestBody AddLinkRequest request
    );

    @DeleteMapping("/links")
    LinkResponse removeLink(
        @RequestHeader("Tg-Chat-Id") long tgChatId,
        @RequestBody RemoveLinkRequest request
    );
}
