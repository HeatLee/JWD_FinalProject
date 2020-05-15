package by.training.finalproject.sort;

import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Room;
import by.training.finalproject.exception.ChainException;

import java.util.ArrayList;
import java.util.List;

public class SortRoomsByStarsAmountStep extends AbstractSortStep {

    public SortRoomsByStarsAmountStep(SortStep nextSorter) {
        next = nextSorter;
    }

    public SortRoomsByStarsAmountStep() {
    }

    @Override
    protected List<Room> sort(Request request) throws ChainException {
        List<Room> sortedRooms = new ArrayList<>();
        for (Room item : roomList) {
            if (item.getHotel().getStars() >= request.getStars()) {
                sortedRooms.add(item);
            }
        }
        return sortedRooms;
    }
}
