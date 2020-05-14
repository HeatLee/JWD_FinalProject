package by.training.finalproject.service;

import by.training.finalproject.entity.Request;
import by.training.finalproject.exception.ServiceException;

import java.util.List;

public interface RequestService {
    Request addRequest(Request request) throws ServiceException;

    List<Request> readUserRequest(int userId) throws ServiceException;
}
