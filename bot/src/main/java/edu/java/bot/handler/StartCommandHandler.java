package edu.java.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class StartCommandHandler implements CommandHandler {
    @Override
    public boolean canHandle(Update update) {
        return update.message() != null && "/start".equals(update.message().text());
    }

    @Override
    public void handle(Update update, TelegramBot bot) {
        bot.execute(new SendMessage(update.message().chat().id(), "Добро пожаловать в бота!"));
    }
}
