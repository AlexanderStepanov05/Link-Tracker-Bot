package edu.java.scrapper.controller;

import edu.java.scrapper.dto.request.AddLinkRequest;
import edu.java.scrapper.dto.request.RemoveLinkRequest;
import edu.java.scrapper.dto.response.LinkResponse;
import edu.java.scrapper.dto.response.ListLinksResponse;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
public class LinksController {

    @PostMapping
    public ResponseEntity<LinkResponse> addLink(
        @RequestHeader("Tg-Chat-Id") long tgChatId,
        @RequestBody AddLinkRequest request
    ) {
        // Заглушка
        return ResponseEntity.ok(
            new LinkResponse(1L, request.link(), OffsetDateTime.now())
        );
    }

    @DeleteMapping
    public ResponseEntity<LinkResponse> removeLink(
        @RequestHeader("Tg-Chat-Id") long tgChatId,
        @RequestBody RemoveLinkRequest request
    ) {
        // Заглушка
        return ResponseEntity.ok(
            new LinkResponse(1L, request.link(), OffsetDateTime.now())
        );
    }

    @GetMapping
    public ResponseEntity<ListLinksResponse> getLinks(
        @RequestHeader("Tg-Chat-Id") long tgChatId
    ) {
        // Заглушка
        return ResponseEntity.ok(
            new ListLinksResponse(List.of(), 0)
        );
    }
}

