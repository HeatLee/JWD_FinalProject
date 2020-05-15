package by.training.finalproject.command.redirect;

import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Room;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.RequestService;
import by.training.finalproject.service.RoomService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateResponseRedirectCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(CreateResponseRedirectCommand.class);
    private static final RequestService REQUEST_SERVICE = ServiceFactory.INSTANCE.getRequestService();
    private static final RoomService ROOM_SERVICE = ServiceFactory.INSTANCE.getRoomService();

    @Override

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Request req = REQUEST_SERVICE.getRequestById(Integer.parseInt(request.getParameter(JSPParameter.REQUEST_ID.getValue())));
            List<Room> sortedRoomList = ROOM_SERVICE.sortRoomsByRequest(req);
            request.setAttribute(Attribute.ROOM_LIST.getAttribute(), sortedRoomList);
            request.setAttribute(Attribute.REQUEST.getAttribute(), req);
            return Page.CREATE_RESPONSE_PAGE.getValue();
        } catch (ServiceException e) {
            LOGGER.warn(e);
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.ADMIN_PAGE.getValue();
        }
    }
}
