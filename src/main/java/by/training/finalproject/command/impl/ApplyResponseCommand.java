package by.training.finalproject.command.impl;

import by.training.finalproject.builder.ResponseBuilder;
import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.RequestStatus;
import by.training.finalproject.entity.Response;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.ServiceFactory;
import by.training.finalproject.service.RequestService;
import by.training.finalproject.service.ResponseService;
import by.training.finalproject.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplyResponseCommand implements Command {
    private static final ResponseService RESPONSE_SERVICE = ServiceFactory.INSTANCE.getResponseService();
    private static final RoomService ROOM_SERVICE = ServiceFactory.INSTANCE.getRoomService();
    private static final RequestService REQUEST_SERVICE = ServiceFactory.INSTANCE.getRequestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int userRequestId = Integer.parseInt(request.getParameter(JSPParameter.REQUEST_ID.getValue()));
            Response userResponse = new ResponseBuilder()
                    .buildRoom(ROOM_SERVICE.getRoomById(
                            Integer.parseInt(request.getParameter(JSPParameter.ROOM_ID.getValue()))))
                    .buildRequest(REQUEST_SERVICE.getRequestById(userRequestId))
                    .build();
            //todo add transaction
            RESPONSE_SERVICE.addResponse(userResponse);
            REQUEST_SERVICE.updateRequestStatusById(userRequestId, RequestStatus.APPROVED);
            return Page.ADMIN_PAGE.getValue();
        } catch (ServiceException e) {
            request.setAttribute(Attribute.ERR_PARAMETER.getAttribute(), e.getMessage());
            return Page.CREATE_RESPONSE_PAGE.getValue();
        }
    }
}
