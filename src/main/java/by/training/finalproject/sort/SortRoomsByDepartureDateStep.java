package by.training.finalproject.sort;

import by.training.finalproject.dao.ResponseDAO;
import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Response;
import by.training.finalproject.entity.Room;
import by.training.finalproject.exception.ChainException;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.factory.DAOFactory;

import java.util.ArrayList;
import java.util.List;

public class SortRoomsByDepartureDateStep extends AbstractSortStep {
    private static final ResponseDAO<Response> RESPONSE_DAO = DAOFactory.INSTANCE.getResponseDAO();

    public SortRoomsByDepartureDateStep(SortStep nextSorter) {
        next = nextSorter;
    }

    public SortRoomsByDepartureDateStep() {
    }

    @Override
    protected List<Room> sort(Request request) throws ChainException {
        try {
            List<Room> sortedRooms = new ArrayList<>();
            for (Room item : roomList) {
                Request dbRequest = RESPONSE_DAO.getRequestByRoomId(item.getId());
                if (dbRequest == null) {
                    sortedRooms.add(item);
                } else {
                    if (request.getDeparture().isBefore(dbRequest.getCheckIn())) {
                        sortedRooms.add(item);
                    }
                }
            }
            return sortedRooms;
        } catch (DAOException e) {
            throw new ChainException("Server error");
        }
    }
}
