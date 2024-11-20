package edu.java.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public interface CommandHandler {
    boolean canHandle(Update update);
    void handle(Update update, TelegramBot bot);
}
