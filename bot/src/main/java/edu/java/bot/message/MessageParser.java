package edu.java.bot.message;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.HelpCommand;
import edu.java.bot.commands.ListCommand;
import edu.java.bot.commands.StartCommand;
import edu.java.bot.commands.TrackCommand;
import edu.java.bot.commands.UntrackCommand;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class MessageParser implements UserMessageProcessor {
    private final List<Command> commands = Arrays.asList(
        new StartCommand(),
        new HelpCommand(),
        new TrackCommand(),
        new UntrackCommand(),
        new ListCommand()
    );


    @Override
    public List<? extends Command> commands() {
        return commands;
    }

    @Override
    public SendMessage process(Update update) {
        String text = update.message().text();
        long chatId = update.message().chat().id();

        for (Command command : commands) {
            if (text.startsWith(command.command())) {
                return command.handle(update);
            }
        }
        return new SendMessage(chatId, "Неизвестная команда");
    }
}
