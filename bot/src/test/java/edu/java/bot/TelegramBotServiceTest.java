package edu.java.bot;

import edu.java.bot.handler.ListCommandHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Chat;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TelegramBotServiceTest {
    @Test
    void testListCommandHandler() {
        TelegramBot bot = mock(TelegramBot.class);
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(update.message()).thenReturn(message);
        when(message.text()).thenReturn("/list");
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(123L);

        ListCommandHandler listCommandHandler = new ListCommandHandler();
        
        // Test canHandle method first
        assert listCommandHandler.canHandle(update);
        
        // Then test handle method
        listCommandHandler.handle(update, bot);

        verify(bot).execute(any());
    }
}
