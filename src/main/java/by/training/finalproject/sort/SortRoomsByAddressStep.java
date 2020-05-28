package by.training.finalproject.sort;

import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Room;
import by.training.finalproject.exception.ChainException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SortRoomsByAddressStep extends AbstractSortStep {
    public SortRoomsByAddressStep(SortStep nextSorter) {
        next = nextSorter;
    }

    public SortRoomsByAddressStep() {
    }

    @Override
    protected List<Room> sort(Request request) throws ChainException {
        List<Room> sortedRooms = new ArrayList<Room>();
        for (Room item : roomList) {
            if (StringUtils.equals(item.getHotel().getAddress().getCountry(), request.getAddress().getCountry()) &&
                    StringUtils.equals(item.getHotel().getAddress().getTown(), request.getAddress().getTown())) {
                sortedRooms.add(item);
            }
        }
        return sortedRooms;
    }
}
