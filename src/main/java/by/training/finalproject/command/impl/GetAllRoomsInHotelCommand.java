package by.training.finalproject.command.impl;

import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.HotelService;
import by.training.finalproject.service.RequestService;
import by.training.finalproject.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllRoomsInHotelCommand implements Command {
    private static final RoomService ROOM_SERVICE = ServiceFactory.INSTANCE.getRoomService();
    private static final HotelService HOTEL_SERVICE = ServiceFactory.INSTANCE.getHotelService();
    private static final RequestService REQUEST_SERVICE = ServiceFactory.INSTANCE.getRequestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute(Attribute.HOTEL_LIST.getAttribute(), HOTEL_SERVICE.getAllHotels());
            request.setAttribute(Attribute.REQUEST_LIST.getAttribute(), REQUEST_SERVICE.readAllRequests());
            int hotelId = Integer.parseInt(request.getParameter(JSPParameter.HOTEL_ID.getValue()));
            request.setAttribute(Attribute.ROOM_LIST.getAttribute(),
                    ROOM_SERVICE.getRoomsByHotel(hotelId));
            return Page.ADMIN_PAGE.getValue();
        } catch (ServiceException e) {
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.ADMIN_PAGE.getValue();
        }
    }
}
