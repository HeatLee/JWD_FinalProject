package by.training.finalproject.sort;

import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Room;
import by.training.finalproject.exception.ChainException;

import java.util.List;

public interface SortStep {
    void setRoomList(List<Room> rooms);

    List<Room> doSort(Request request) throws ChainException;

    SortStep next();
}
