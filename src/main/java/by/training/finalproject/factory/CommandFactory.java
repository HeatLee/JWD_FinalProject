package by.training.finalproject.factory;

import by.training.finalproject.command.Command;
import by.training.finalproject.command.CommandParameter;

public class CommandFactory {
    public Command getCommand(String commandName) {
        return CommandParameter.valueOf(commandName).getCommand();
    }
}
