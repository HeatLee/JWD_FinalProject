package by.training.finalproject.command.redirect;

import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.Request;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.HotelService;
import by.training.finalproject.service.RequestService;
import by.training.finalproject.service.RoomService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminPageRedirectCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminPageRedirectCommand.class);
    private static final RequestService REQUEST_SERVICE = ServiceFactory.INSTANCE.getRequestService();
    private static final HotelService HOTEL_SERVICE = ServiceFactory.INSTANCE.getHotelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute(Attribute.REQUEST_LIST.getAttribute(), REQUEST_SERVICE.readAllRequests());
            request.setAttribute(Attribute.HOTEL_LIST.getAttribute(), HOTEL_SERVICE.getAllHotels());
            return Page.ADMIN_PAGE.getValue();
        } catch (ServiceException e) {
            LOGGER.warn(e);
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.ADMIN_PAGE.getValue();
        }
    }
}
