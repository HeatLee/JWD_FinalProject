package by.training.finalproject.service.impl;

import by.training.finalproject.dao.RequestDAO;
import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.RequestStatus;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.factory.DAOFactory;
import by.training.finalproject.service.RequestService;
import by.training.finalproject.validator.Validator;
import by.training.finalproject.validator.impl.RequestValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class RequestServiceImpl implements RequestService {
    private static final RequestDAO<Request> REQUEST_DAO = DAOFactory.INSTANCE.getRequestDAO();
    
    private static final RequestServiceImpl SERVICE;

    static {
        SERVICE = new RequestServiceImpl();
    }

    private final Logger LOGGER = Logger.getLogger(RequestServiceImpl.class);
    private final Validator<Request> validator = new RequestValidator();

    private RequestServiceImpl() {
    }

    public static RequestServiceImpl getInstance() {
        return SERVICE;
    }

    @Override
    public Request addRequest(Request request) throws ServiceException {
        try {
            validator.validate(request);
            REQUEST_DAO.add(request);
            return request;
        } catch (ValidatorException e) {
            LOGGER.info(e);
            throw new ServiceException(e.getMessage());
        } catch (DAOException ex) {
            LOGGER.warn(ex);
            throw new ServiceException("Server error. Sorry for that :(");
        }
    }

    @Override
    public List<Request> readUserRequest(int userId) throws ServiceException {
        try {
            return REQUEST_DAO.readRequestsByUserId(userId);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Request> readAllRequests() throws ServiceException {
        try {
            return REQUEST_DAO.readAll();
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error. Sorry for that :(");
        }
    }

    @Override
    public Request getRequestById(int id) throws ServiceException {
        try {
            return REQUEST_DAO.readById(id);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        }
    }

    @Override
    public void updateRequestStatusById(int id, RequestStatus status) throws ServiceException {
        try{
            Request request = REQUEST_DAO.readById(id);
            request.setStatus(status);
            REQUEST_DAO.update(request);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        }
    }
}
