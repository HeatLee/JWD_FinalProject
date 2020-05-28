package by.training.finalproject.service.impl;

import by.training.finalproject.dao.AddressDAO;
import by.training.finalproject.entity.Address;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.factory.DAOFactory;
import by.training.finalproject.service.AddressService;
import org.apache.log4j.Logger;

public class AddressServiceImpl implements AddressService {
    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class);
    private static final AddressService INSTANCE = new AddressServiceImpl();
    private static final AddressDAO<Address> ADDRESS_DAO = DAOFactory.INSTANCE.getAddressDAO();

    private AddressServiceImpl() {
    }

    public static AddressService getInstance() {
        return INSTANCE;
    }

    @Override
    public Address getAddressByFullData(Address address) throws ServiceException {
        try {
            return ADDRESS_DAO.getAddressByFullData(address.getCountry(), address.getTown());
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean isContains(Address address) throws ServiceException {
        try {
            return ADDRESS_DAO.getAddressByFullData(address.getCountry(), address.getTown()) != null;
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e.getMessage());
        }
    }
}
