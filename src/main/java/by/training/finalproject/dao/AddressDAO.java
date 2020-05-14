package by.training.finalproject.dao;

import by.training.finalproject.entity.Address;
import by.training.finalproject.exception.DAOException;

public interface AddressDAO<T> extends CommonDAO<T>{
    Address getAddressByFullData(String country, String town) throws DAOException;

}
