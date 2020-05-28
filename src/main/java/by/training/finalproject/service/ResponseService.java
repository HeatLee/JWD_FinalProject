package by.training.finalproject.service;

import by.training.finalproject.entity.Response;
import by.training.finalproject.entity.Room;
import by.training.finalproject.exception.ServiceException;

public interface ResponseService {
    void addResponse(Response response) throws ServiceException;

    void deleteResponseByRequestId(int requestId) throws ServiceException;

    Response getResponseByRequestId(int requestId) throws ServiceException;
}
