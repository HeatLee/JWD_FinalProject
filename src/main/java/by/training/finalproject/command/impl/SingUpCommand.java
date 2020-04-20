package by.training.finalproject.command.impl;

import by.training.finalproject.builder.UserBuilder;
import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.User;
import by.training.finalproject.entity.UserRole;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SingUpCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SingUpCommand.class);
    private static final UserServiceImpl SERVICE = ServiceFactory.INSTANCE.geUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            User user = new UserBuilder()
                    .buildLogin(request.getParameter(JSPParameter.LOGIN.getValue()))
                    .buildEmail(request.getParameter(JSPParameter.EMAIL.getValue()))
                    .buildPassword(request.getParameter(JSPParameter.PASSWORD.getValue()))
                    .buildRole(UserRole.USER)
                    .build();
            user = SERVICE.signUp(user);
            HttpSession session = request.getSession();
//            session.setAttribute(Attribute.USER_LOGIN.getAttribute(), user.getLogin());
//            session.setAttribute(Attribute.USER_ROLE.getAttribute(), user.getUserRole().toString());
            session.setAttribute(Attribute.USER.getAttribute(), user);
            return Page.INDEX.getValue();
        } catch (ServiceException e) {
            LOGGER.info(e);
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.SIGN_UP.getValue();
        }
    }
}
