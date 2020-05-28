package by.training.finalproject.command.impl;

import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.Response;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.ResponseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserResponseCommand implements Command {
    private static final ResponseService RESPONSE_SERVICE = ServiceFactory.INSTANCE.getResponseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int requestId = Integer.parseInt(request.getParameter(JSPParameter.REQUEST_ID.getValue()));
            Response userResponse = RESPONSE_SERVICE.getResponseByRequestId(requestId);
            request.setAttribute(Attribute.RESPONSE.getAttribute(), userResponse);
            return Page.ROOM_INFO.getValue();
        } catch (ServiceException e) {
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.PROFILE.getValue();
        }
    }
}
