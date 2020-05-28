package by.training.finalproject.command.impl;

import by.training.finalproject.builder.RoomBuilder;
import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.Hotel;
import by.training.finalproject.entity.Room;
import by.training.finalproject.entity.RoomStatus;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.HotelService;
import by.training.finalproject.service.RequestService;
import by.training.finalproject.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class AddNewHotelRoomCommand implements Command {
    private static final HotelService HOTEL_SERVICE = ServiceFactory.INSTANCE.getHotelService();
    private static final RoomService ROOM_SERVICE = ServiceFactory.INSTANCE.getRoomService();
    private static final RequestService REQUEST_SERVICE = ServiceFactory.INSTANCE.getRequestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute(Attribute.REQUEST_LIST.getAttribute(), REQUEST_SERVICE.readAllRequests());
            request.setAttribute(Attribute.HOTEL_LIST.getAttribute(), HOTEL_SERVICE.getAllHotels());
            int hotelId = Integer.parseInt(request.getParameter(JSPParameter.HOTEL_ID.getValue()));
            Hotel hotel = HOTEL_SERVICE.getHotelById(hotelId);
            Room room = new RoomBuilder()
                    .buildPrice(BigDecimal.valueOf(
                            Double.parseDouble(request.getParameter(JSPParameter.PRICE.getValue()))))
                    .buildCapacity(Integer.parseInt(
                            request.getParameter(JSPParameter.CAPACITY.getValue())
                    ))
                    .buildStatus(RoomStatus.AVAILABLE)
                    .buildHotel(hotel)
                    .build();
            ROOM_SERVICE.addHotelRoom(room);
            return Page.ADMIN_PAGE.getValue();
        } catch (ServiceException e) {
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.ADMIN_PAGE.getValue();
        }
    }
}
