package edu.java.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class HelpCommandHandler implements CommandHandler {
    @Override
    public boolean canHandle(Update update) {
        return update.message() != null && "/help".equals(update.message().text());
    }

    @Override
    public void handle(Update update, TelegramBot bot) {
        String helpMessage = """
                Доступные команды:
                /start - Начать работу
                /help - Показать справку
                /track <url> - Начать отслеживание ресурса
                /untrack <url> - Остановить отслеживание ресурса
                /list - Показать список отслеживаемых ресурсов
                """;
        bot.execute(new SendMessage(update.message().chat().id(), helpMessage));
    }
}

