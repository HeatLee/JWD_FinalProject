package by.training.finalproject.dao;

import by.training.finalproject.entity.Hotel;
import by.training.finalproject.exception.DAOException;

/**
 * Specific interface for working with data store, that contains Hotel entities
 * @param <T> type for parent interface
 */
public interface HotelDAO<T> extends CommonDAO<T> {
    /**
     *  looking for a Hotel entity by its name
     * @param name string field, that represents name of the Hotel entity
     * @return full Hotel entity from data store
     * @throws DAOException thrown, when something goes wrong while getting hotel from data store
     */
    Hotel findHotelByName(String name) throws DAOException;

    /**
     * Add hotel to data store with undefined hotel location
     * @param hotel full hotel entity
     * @throws DAOException thrown, when something goes wrong while setting new hotel entity to data store
     */
    void addWithAddress(Hotel hotel) throws DAOException;
}
