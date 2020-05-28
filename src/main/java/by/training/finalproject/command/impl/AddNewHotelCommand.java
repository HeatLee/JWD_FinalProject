package by.training.finalproject.command.impl;

import by.training.finalproject.builder.AddressBuilder;
import by.training.finalproject.builder.HotelBuilder;
import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Hotel;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.HotelService;
import by.training.finalproject.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewHotelCommand implements Command {
    private static final RequestService REQUEST_SERVICE = ServiceFactory.INSTANCE.getRequestService();
    private static final HotelService HOTEL_SERVICE = ServiceFactory.INSTANCE.getHotelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Address address = new AddressBuilder()
                    .buildCountry(request.getParameter(JSPParameter.COUNTRY.getValue()))
                    .buildTown(request.getParameter(JSPParameter.TOWN.getValue()))
                    .build();
            Hotel hotel = new HotelBuilder()
                    .buildName(request.getParameter(JSPParameter.HOTEL_NAME.getValue()))
                    .buildStars(Integer.parseInt(request.getParameter(JSPParameter.STARS.getValue())))
                    .buildAddress(address)
                    .build();
            request.setAttribute(Attribute.REQUEST_LIST.getAttribute(), REQUEST_SERVICE.readAllRequests());
            request.setAttribute(Attribute.HOTEL_LIST.getAttribute(), HOTEL_SERVICE.getAllHotels());
            HOTEL_SERVICE.addHotel(hotel);
            return Page.ADMIN_PAGE.getValue();
        } catch (ServiceException e) {
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.ADMIN_PAGE.getValue();
        }
    }
}
