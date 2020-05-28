package by.training.finalproject.sort;

import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Room;
import by.training.finalproject.exception.ChainException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSortStep implements SortStep {
    protected List<Room> roomList = new ArrayList<>();
    protected SortStep next;

    @Override
    public void setRoomList(List<Room> rooms) {
        roomList.clear();
        roomList.addAll(rooms);
    }

    @Override
    public List<Room> doSort(Request request) throws ChainException {
        List<Room> sortResult = new ArrayList<>(sort(request));
        if (next != null) {
            next.setRoomList(sortResult);
            return next.doSort(request);
        }
        return sortResult;
    }

    protected abstract List<Room> sort(Request request) throws ChainException;

    @Override
    public SortStep next() {
        return next;
    }
}
