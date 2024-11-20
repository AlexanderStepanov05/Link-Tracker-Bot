package edu.java.bot;

import edu.java.bot.handler.ListCommandHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TelegramBotServiceTest {
    @Test
    void testListCommandHandler() {
        TelegramBot bot = mock(TelegramBot.class);
        Update update = mock(Update.class);

        when(update.message().text()).thenReturn("/list");

        ListCommandHandler listCommandHandler = new ListCommandHandler();
        listCommandHandler.handle(update, bot);

        verify(bot).execute(any());
    }
}
