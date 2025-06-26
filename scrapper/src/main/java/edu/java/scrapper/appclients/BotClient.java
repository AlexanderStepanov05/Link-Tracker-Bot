package edu.java.scrapper.appclients;

import edu.java.scrapper.dto.request.LinkUpdateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BotClient {
    @PostMapping("/updates")
    void sendUpdate(@RequestBody LinkUpdateRequest request);
}
