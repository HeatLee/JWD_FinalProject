package by.training.finalproject.command.impl;

import by.training.finalproject.builder.UserBuilder;
import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.User;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserInfoCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ChangeUserInfoCommand.class);
    private static final UserService SERVICE = ServiceFactory.INSTANCE.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User sessionUser = (User) request.getSession().getAttribute(Attribute.USER.getAttribute());
            String pass = request.getParameter(JSPParameter.PASSWORD.getValue());
            String passConf = request.getParameter(JSPParameter.PASSWORD_CONFIRMATION.getValue());
            String oldPass = request.getParameter(JSPParameter.OLD_PASSWORD.getValue());
            String dbOldPass = SERVICE.getPasswordByLogin(sessionUser.getLogin());
            User newUser = new UserBuilder()
                    .buildRole(sessionUser.getUserRole())
                    .buildUserId(sessionUser.getUserId())
                    .buildLogin(request.getParameter(JSPParameter.LOGIN.getValue()))
                    .buildEmail(request.getParameter(JSPParameter.EMAIL.getValue()))
                    .buildPassword(StringUtils.isBlank(oldPass)
                            ? dbOldPass:
                            StringUtils.equals(pass, passConf) && StringUtils.equals(oldPass, dbOldPass)
                                    ? pass :
                                    StringUtils.EMPTY)
                    .build();
            SERVICE.editUser(newUser);
            request.getSession().setAttribute(Attribute.USER.getAttribute(), newUser);
            return Page.PROFILE.getValue();
        } catch (ServiceException e) {
            LOGGER.warn(e);
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.PROFILE.getValue();
        }
    }
}
