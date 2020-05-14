package by.training.finalproject.command;

import by.training.finalproject.command.impl.*;
import by.training.finalproject.command.redirect.IndexRedirectCommand;
import by.training.finalproject.command.redirect.ProfileRedirectCommand;
import by.training.finalproject.command.redirect.SignInRedirectCommand;
import by.training.finalproject.command.redirect.SingUpRedirectCommand;

public enum CommandParameter {
    SIGN_UP(new SingUpCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_OUT(new SignOutCommand()),
    CHANGE_USER(new ChangeUserInfoCommand()),
    INDEX_PAGE(new IndexRedirectCommand()),
    SIGN_UP_PAGE(new SingUpRedirectCommand()),
    SIGN_IN_PAGE(new SignInRedirectCommand()),
    PROFILE_PAGE(new ProfileRedirectCommand()),
    SEND_REQUEST(new AddNewRequestCommand());

    private Command command;

    CommandParameter(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
