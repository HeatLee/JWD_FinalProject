package by.training.finalproject.service;

import by.training.finalproject.entity.Hotel;
import by.training.finalproject.exception.ServiceException;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels() throws ServiceException;

    void addHotel(Hotel hotel) throws ServiceException;

    Hotel getHotelById(int hotelId) throws ServiceException;
}
