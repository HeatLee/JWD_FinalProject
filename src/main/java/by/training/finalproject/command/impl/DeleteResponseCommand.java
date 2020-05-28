package by.training.finalproject.command.impl;

import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.Request;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteResponseCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Request> requestList = null;
        try {
            int requestId = Integer.parseInt(request.getParameter(JSPParameter.REQUEST_ID.getValue()));
            requestList = ServiceFactory.INSTANCE.getRequestService().readAllRequests();
            ServiceFactory.INSTANCE.getResponseService().deleteResponseByRequestId(requestId);
            request.setAttribute(Attribute.REQUEST_LIST.getAttribute(), requestList);
        } catch (ServiceException e) {
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
        }
        request.setAttribute(Attribute.REQUEST_LIST.getAttribute(), requestList);
        return Page.ADMIN_PAGE.getValue();
    }
}
