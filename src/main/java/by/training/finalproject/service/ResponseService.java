package by.training.finalproject.service;

import by.training.finalproject.entity.Response;
import by.training.finalproject.exception.ServiceException;

public interface ResponseService {
    void addResponse(Response response) throws ServiceException;

}
