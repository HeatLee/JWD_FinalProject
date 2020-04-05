package by.training.finalproject.dao;

import by.training.finalproject.entity.Room;

import java.util.List;

public interface RoomDAO extends CommonDAO<Room> {

    List<Room> readByRoomStatus(String roomStatus);

    List<Room> readByCapacity(String capacity);

}
