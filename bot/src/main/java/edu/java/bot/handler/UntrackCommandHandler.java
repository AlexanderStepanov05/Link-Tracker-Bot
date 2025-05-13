package edu.java.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.exception.LinkNotFoundException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class UntrackCommandHandler implements CommandHandler {
    private final Set<String> trackedLinks = new HashSet<>(); // Заглушка для данных

    @Override
    public boolean canHandle(Update update) {
        return update.message() != null && update.message().text() != null && update.message().text().startsWith("/untrack");
    }

    @Override
    public void handle(Update update, TelegramBot bot) {
        Long chatId = update.message().chat().id();
        String[] parts = update.message().text().split("\\s+", 2);
        if (parts.length < 2) {
            bot.execute(new SendMessage(chatId, "Укажите URL после команды /untrack"));
            return;
        }
        String url = parts[1];
        if (!trackedLinks.contains(url)) {
            throw new LinkNotFoundException("Ссылка " + url + " не отслеживается");
        }
        trackedLinks.remove(url);
        bot.execute(new SendMessage(chatId, "Ссылка " + url + " удалена из отслеживания"));
    }
}
