package by.training.finalproject.dao;

import by.training.finalproject.entity.Address;
import by.training.finalproject.exception.DAOException;

/**
 * Specific interface for working with data store, that contains Address entities
 * @param <T> type for parent interface
 */
public interface AddressDAO<T> extends CommonDAO<T>{

    /**
     * Gets full Address entity by his fields
     * @param country string field, that contains name of country in full address
     * @param town string field, that contains name of town in full address
     * @return full Address entity
     * @throws DAOException thrown, when something goes wrong while getting Address from data store
     */
    Address getAddressByFullData(String country, String town) throws DAOException;

}
