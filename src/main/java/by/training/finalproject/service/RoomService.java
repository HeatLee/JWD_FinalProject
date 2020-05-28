package by.training.finalproject.service;

import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Room;
import by.training.finalproject.exception.ServiceException;

import java.util.List;

public interface RoomService {
    List<Room> sortRoomsByRequest(Request request) throws ServiceException;

    Room getRoomById(int id) throws ServiceException;

    List<Room> getAllRooms() throws ServiceException;

    List<Room> getRoomsByHotel(int hotelId) throws ServiceException;

    void addHotelRoom(Room newRoom) throws ServiceException;
}
