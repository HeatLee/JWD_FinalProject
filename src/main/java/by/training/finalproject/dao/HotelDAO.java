package by.training.finalproject.dao;

import by.training.finalproject.entity.Hotel;
import by.training.finalproject.exception.DAOException;

public interface HotelDAO<T> extends CommonDAO<T> {
    Hotel findHotelByName(String name) throws DAOException;

    void addWithAddress(Hotel hotel) throws DAOException;
}
