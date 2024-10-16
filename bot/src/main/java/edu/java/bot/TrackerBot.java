package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.message.MessageParser;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TrackerBot implements Bot {
    private final TelegramBot bot;
    private final MessageParser messageParser;

    public TrackerBot() {
        this.bot = new TelegramBot("7717771695:AAHu1jNw11IFbyEtK9qLFZ7WJAWSzCQiUqE");
        this.messageParser = new MessageParser();
    }

    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(BaseRequest<T, R> request) {
        bot.execute(request);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            SendMessage sendMessage = messageParser.process(update);
            if (sendMessage != null) {
                execute(sendMessage);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Override
    public void start() {
        bot.setUpdatesListener(this);

    }

    @Override
    public void close() {
        bot.removeGetUpdatesListener();
    }
}
