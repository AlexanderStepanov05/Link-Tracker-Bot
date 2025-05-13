package edu.java.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.exception.DuplicateLinkException;
import edu.java.bot.exception.LinkNotFoundException;
import edu.java.bot.handler.CommandHandler;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotService {
    private final TelegramBot bot;
    private final List<CommandHandler> handlers;

    public TelegramBotService(ApplicationConfig config, List<CommandHandler> handlers) {
        this.bot = new TelegramBot(config.getTelegramToken());
        this.handlers = handlers;
        bot.setUpdatesListener(this::processUpdates);
    }

    @PostConstruct
    private void init() {
        setupBotCommands();
    }

    private int processUpdates(List<Update> updates) {
        updates.forEach(update -> {
            try {
                boolean handled = false;
                for (CommandHandler handler : handlers) {
                    if (handler.canHandle(update)) {
                        handler.handle(update, bot);
                        handled = true;
                        break;
                    }
                }
                if (!handled && update.message() != null) {
                    bot.execute(new SendMessage(update.message().chat().id(), "Неизвестная команда."));
                }
            } catch (DuplicateLinkException | LinkNotFoundException e) {
                bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
            } catch (Exception e) {
                bot.execute(new SendMessage(update.message().chat().id(), "Произошла ошибка."));
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void setupBotCommands() {
        bot.execute(new SetMyCommands(
            new BotCommand("/start", "Начать работу с ботом"),
            new BotCommand("/help", "Справка по боту"),
            new BotCommand("/track", "Отслеживать ресурс"),
            new BotCommand("/untrack", "Прекратить отслеживать ресурс"),
            new BotCommand("/list", "Список отслеживаемых ресурсов")
        ));
    }
}
