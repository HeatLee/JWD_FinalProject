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

public class SortRoomsByCheckInDateStep extends AbstractSortStep {
    private static final ResponseDAO<Response> RESPONSE_DAO = DAOFactory.INSTANCE.getResponseDAO();

    public SortRoomsByCheckInDateStep(SortStep nextSorter) {
    }

    public SortRoomsByCheckInDateStep() {
    }

    @Override
    protected List<Room> sort(Request request) throws ChainException {
        try{
            List<Room> sortResult = new ArrayList<Room>();
            for (Room item : roomList) {
                Request dbRequest = RESPONSE_DAO.getRequestByRoomId(item.getId());
                if (dbRequest == null) {
                    sortResult.add(item);
                } else {
                    if (request.getCheckIn().isAfter(dbRequest.getDeparture())) {
                        sortResult.add(item);
                    }
                }
            }
            return sortResult;
        } catch (DAOException e) {
            throw new ChainException("Server error");
        }
    }
}
