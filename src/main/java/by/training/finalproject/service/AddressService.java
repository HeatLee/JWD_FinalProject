package by.training.finalproject.service;

import by.training.finalproject.entity.Address;
import by.training.finalproject.exception.ServiceException;

public interface AddressService {
    Address getAddressByFullData(Address address) throws ServiceException;

    boolean isContains(Address address) throws ServiceException;
}
