package by.training.finalproject.command;

import by.training.finalproject.command.impl.*;
import by.training.finalproject.command.redirect.*;

public enum CommandParameter {
    SIGN_UP(new SingUpCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_UP_PAGE(new SingUpRedirectCommand()),
    SIGN_IN_PAGE(new SignInRedirectCommand()),
    INDEX_PAGE(new IndexRedirectCommand()),

    SIGN_OUT(new SignOutCommand()),
    CHANGE_USER(new ChangeUserInfoCommand()),
    PROFILE_PAGE(new ProfileRedirectCommand()),
    SEND_REQUEST(new AddNewRequestCommand()),

    ADMIN_PAGE(new AdminPageRedirectCommand());
    ADMIN_PAGE(new AdminPageRedirectCommand()),
    CREATE_RESPONSE_PAGE(new CreateResponseRedirectCommand()),

    private Command command;

    CommandParameter(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
