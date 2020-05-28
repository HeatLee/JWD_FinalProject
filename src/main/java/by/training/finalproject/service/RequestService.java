package by.training.finalproject.service;

import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.RequestStatus;
import by.training.finalproject.exception.ServiceException;

import java.util.List;

public interface RequestService {
    Request addRequest(Request request) throws ServiceException;

    List<Request> readUserRequest(int userId) throws ServiceException;

    List<Request> readAllRequests() throws ServiceException;

    Request getRequestById(int id) throws ServiceException;

    void updateRequestStatusById(int id, RequestStatus status) throws ServiceException;

    void deleteRequestById(int id) throws ServiceException;
}
