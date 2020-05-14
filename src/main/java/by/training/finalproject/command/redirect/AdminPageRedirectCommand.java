package by.training.finalproject.command.redirect;

import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.Request;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.RequestService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminPageRedirectCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminPageRedirectCommand.class);
    private final static RequestService REQUEST_SERVICE = ServiceFactory.INSTANCE.getRequestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Request> requests = REQUEST_SERVICE.readAllRequests();
            request.setAttribute(Attribute.REQUEST_LIST.getAttribute(), requests);
            return Page.ADMIN_PAGE.getValue();
        } catch (ServiceException e) {
            LOGGER.warn(e);
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.ADMIN_PAGE.getValue();
        }
    }
}
