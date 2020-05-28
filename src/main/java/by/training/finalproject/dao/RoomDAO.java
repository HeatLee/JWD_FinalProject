package by.training.finalproject.dao;

import by.training.finalproject.entity.Room;
import by.training.finalproject.entity.RoomStatus;
import by.training.finalproject.exception.DAOException;

import java.util.List;

public interface RoomDAO<T> extends CommonDAO<T> {
    List<Room> readByRoomStatus(RoomStatus roomStatus) throws DAOException;

    List<Room> readByCapacity(int capacity) throws DAOException;

    List<Room> readByHotelId(int hotelId) throws DAOException;
}
