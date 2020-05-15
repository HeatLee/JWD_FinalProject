package by.training.finalproject.service.impl;

import by.training.finalproject.dao.ResponseDAO;
import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Response;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.factory.DAOFactory;
import by.training.finalproject.service.ResponseService;
import by.training.finalproject.validator.Validator;
import by.training.finalproject.validator.impl.AddressValidator;
import by.training.finalproject.validator.impl.RequestValidator;
import by.training.finalproject.validator.impl.RoomValidator;
import org.apache.log4j.Logger;

public class ResponseServiceImpl implements ResponseService {
    private static final Logger LOGGER = Logger.getLogger(ResponseServiceImpl.class);
    private static final ResponseDAO<Response> RESPONSE_DAO = DAOFactory.INSTANCE.getResponseDAO();

    private ResponseServiceImpl() {
    }
    private static final ResponseService SERVICE;
    static {
        SERVICE = new ResponseServiceImpl();
    }
    public static ResponseService getInstance() {
        return SERVICE;
    }

    @Override
    public void addResponse(Response response) throws ServiceException{
        try{
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
}
