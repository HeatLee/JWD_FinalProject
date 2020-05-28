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
    DELETE_USER_REQUEST(new DeleteUserRequestCommand()),
    GET_USER_RESPONSE(new GetUserResponseCommand()),

    ADMIN_PAGE(new AdminPageRedirectCommand()),
    CREATE_RESPONSE_PAGE(new CreateResponseRedirectCommand()),
    APPLY_RESPONSE(new ApplyResponseCommand()),
    DELETE_RESPONSE(new DeleteResponseCommand()),
    SHOW_HOTEL_ROOMS(new GetAllRoomsInHotelCommand()),
    ADD_NEW_HOTEL(new AddNewHotelCommand()),
    ADD_ROOM(new AddNewHotelRoomCommand());

    private Command command;

    CommandParameter(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
