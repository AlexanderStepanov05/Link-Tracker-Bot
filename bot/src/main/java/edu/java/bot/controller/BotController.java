package edu.java.bot.controller;

import edu.java.bot.dto.LinkUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updates")
public class BotController {
    @PostMapping
    public ResponseEntity<?> handleUpdate(@RequestBody LinkUpdateRequest request) {
        // Обработка обновлений от Scrapper
        return ResponseEntity.ok().build();
    }
}
