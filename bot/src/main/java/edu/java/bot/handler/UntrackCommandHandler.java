package edu.java.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class UntrackCommandHandler implements CommandHandler {

    @Override
    public boolean canHandle(Update update) {
        return false;
    }

    @Override
    public void handle(Update update, TelegramBot bot) {

    }
}
