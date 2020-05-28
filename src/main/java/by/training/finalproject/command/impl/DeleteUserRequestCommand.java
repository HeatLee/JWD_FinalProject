package by.training.finalproject.command.impl;

import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.User;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserRequestCommand implements Command {
    private static final RequestService REQUEST_SERVICE = ServiceFactory.INSTANCE.getRequestService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute(Attribute.USER.getAttribute());
            int requestId = Integer.parseInt(request.getParameter(JSPParameter.REQUEST_ID.getValue()));
            REQUEST_SERVICE.deleteRequestById(requestId);
            request.setAttribute(Attribute.REQUEST_LIST.getAttribute(),
                    REQUEST_SERVICE.readUserRequest(user.getUserId()));
            return Page.PROFILE.getValue();
        } catch (ServiceException e) {
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.PROFILE.getValue();
        }
    }
}
