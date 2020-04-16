package by.training.finalproject.command;

import by.training.finalproject.command.redirect.IndexRedirectCommand;
import by.training.finalproject.command.impl.SingUpCommand;
import by.training.finalproject.command.redirect.SingUpRedirectCommand;

public enum CommandParameter {
    SIGN_UP(new SingUpCommand()),
    INDEX_PAGE(new IndexRedirectCommand()),
    SIGN_UP_PAGE(new SingUpRedirectCommand());

    private Command command;

    CommandParameter(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
