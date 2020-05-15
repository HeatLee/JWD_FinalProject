package by.training.finalproject.sort;

import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Room;
import by.training.finalproject.exception.ChainException;

import java.util.ArrayList;
import java.util.List;

public class SortRoomsByCapacityStep extends AbstractSortStep {

    public SortRoomsByCapacityStep(SortStep nextSorter) {
        next = nextSorter;
    }

    public SortRoomsByCapacityStep() {
    }

    @Override
    protected List<Room> sort(Request request) throws ChainException {
            List<Room> sortedRooms = new ArrayList<Room>();
            for (Room item : roomList) {
                if (item.getCapacity() >= request.getCapacity()) {
                    sortedRooms.add(item);
                }
            }
            return sortedRooms;
    }
}
