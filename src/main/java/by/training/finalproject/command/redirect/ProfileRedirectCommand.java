package by.training.finalproject.command.redirect;

import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.User;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProfileRedirectCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Request> requestList = ServiceFactory.INSTANCE.getRequestService()
                    .readUserRequest(
                            ((User)request.getSession().getAttribute(Attribute.USER.getAttribute()))
                                    .getUserId());
            request.setAttribute(Attribute.REQUEST_LIST.getAttribute(), requestList);
            return Page.PROFILE.getValue();
        } catch (ServiceException e) {
            return Page.PROFILE.getValue();
        }
    }
}
