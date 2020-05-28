package by.training.finalproject.service.impl;

import by.training.finalproject.dao.ResponseDAO;
import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Response;
import by.training.finalproject.entity.Room;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.factory.DAOFactory;
import by.training.finalproject.service.ResponseService;
import by.training.finalproject.validator.Validator;
import by.training.finalproject.validator.impl.AddressValidator;
import org.apache.log4j.Logger;

public class ResponseServiceImpl implements ResponseService {
    private static final Logger LOGGER = Logger.getLogger(ResponseServiceImpl.class);
    private static final ResponseDAO<Response> RESPONSE_DAO = DAOFactory.INSTANCE.getResponseDAO();
    private static final ResponseService INSTANCE = new ResponseServiceImpl();

    private ResponseServiceImpl() {
    }

    public static ResponseService getInstance() {
        return INSTANCE;
    }

    @Override
    public Response getResponseByRequestId(int requestId) throws ServiceException {
        try {
            return RESPONSE_DAO.getResponseByRequestId(requestId);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        }
    }

    @Override
    public void addResponse(Response response) throws ServiceException {
        try {
            Validator<Address> addressValidator = new AddressValidator();
            addressValidator.validate(response.getRequest().getAddress());
            addressValidator.validate(response.getRoom().getHotel().getAddress());
            RESPONSE_DAO.add(response);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        } catch (ValidatorException e) {
            LOGGER.warn(e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteResponseByRequestId(int requestId) throws ServiceException {
        try {
            Response response = RESPONSE_DAO.getResponseByRequestId(requestId);
            if (response == null) {
                throw new ServiceException("No response for such request");
            }
            RESPONSE_DAO.delete(response);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        }
    }
}
