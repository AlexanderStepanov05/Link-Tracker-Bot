package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class StartCommand implements Command {
    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Регистрация пользователя";
    }

    @Override
    public SendMessage handle(Update update) {
        long chatId = update.message().chat().id();
        System.out.println("Пользователь " + chatId + " зарегистрировался");
        return new SendMessage(chatId, "Вы успешно зарегистрировались!");
    }
}
