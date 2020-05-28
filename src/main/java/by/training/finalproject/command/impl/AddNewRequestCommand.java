package by.training.finalproject.command.impl;

import by.training.finalproject.builder.AddressBuilder;
import by.training.finalproject.builder.RequestBuilder;
import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.RequestStatus;
import by.training.finalproject.entity.User;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.AddressService;
import by.training.finalproject.service.RequestService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddNewRequestCommand implements Command {
    private final static Logger LOGGER = Logger.getLogger(AddNewRequestCommand.class);
    private final static RequestService REQUEST_SERVICE = ServiceFactory.INSTANCE.getRequestService();
    private final static AddressService ADDRESS_SERVICE = ServiceFactory.INSTANCE.getAddressService();

    private static final String NO_SUCH_ADDRESS_MESSAGE = "No such address available";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Address address = new AddressBuilder()
                    .buildTown(request.getParameter(JSPParameter.TOWN.getValue()))
                    .buildCountry(request.getParameter(JSPParameter.COUNTRY.getValue()))
                    .build();
            if (!ADDRESS_SERVICE.isContains(address)) {
                request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), NO_SUCH_ADDRESS_MESSAGE);
                return Page.INDEX.getValue();
            }
            address = ADDRESS_SERVICE.getAddressByFullData(address);
            User user = (User) request.getSession().getAttribute(Attribute.USER.getAttribute());
            Request req = new RequestBuilder()
                    .buildReservationUser(user)
                    .buildAddress(address)
                    .buildCapacity(Integer.parseInt(request.getParameter(JSPParameter.CAPACITY.getValue())))
                    .buildCheckInDate(
                            LocalDate.parse(request.getParameter(JSPParameter.CHECK_IN.getValue()), FORMATTER))
                    .buildDepartureDate(
                            LocalDate.parse(request.getParameter(JSPParameter.DEPARTURE.getValue()), FORMATTER))
                    .buildStatus(RequestStatus.PROCESS.getId())
                    .buildStars(Integer.parseInt(request.getParameter(JSPParameter.STARS.getValue())))
                    .build();
            REQUEST_SERVICE.addRequest(req);
            return Page.INDEX.getValue();
        } catch (ServiceException e) {
            LOGGER.warn(e);
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.INDEX.getValue();
        }
    }
}
