package edu.java.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class ListCommandHandler implements CommandHandler {
    private final Set<String> trackedLinks = new HashSet<>(); // Заглушка для данных

    @Override
    public boolean canHandle(Update update) {
        return update.message() != null && "/list".equals(update.message().text());
    }

    @Override
    public void handle(Update update, TelegramBot bot) {
        Long chatId = update.message().chat().id();
        if (trackedLinks.isEmpty()) {
            bot.execute(new SendMessage(chatId, "На данный момент вы не отслеживаете ни одной ссылки."));
        } else {
            String links = "Отслеживаемые ссылки:\n" + String.join("\n", trackedLinks);
            bot.execute(new SendMessage(chatId, links));
        }
    }
}
