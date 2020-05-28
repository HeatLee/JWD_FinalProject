package by.training.finalproject.service.impl;

import by.training.finalproject.dao.RoomDAO;
import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Room;
import by.training.finalproject.exception.ChainException;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.factory.DAOFactory;
import by.training.finalproject.service.RoomService;
import by.training.finalproject.sort.*;
import by.training.finalproject.validator.Validator;
import by.training.finalproject.validator.impl.RoomValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private static final Logger LOGGER = Logger.getLogger(RoomService.class);
    private static final RoomDAO<Room> ROOM_DAO = DAOFactory.INSTANCE.getRoomDAO();
    private static final Validator<Room> ROOM_VALIDATOR = new RoomValidator();
    private static final RoomService INSTANCE = new RoomServiceImpl();

    public static RoomService getInstance() {
        return INSTANCE;
    }

    private RoomServiceImpl() {
    }

    @Override
    public List<Room> getRoomsByHotel(int hotelId) throws ServiceException {
        try{
            return ROOM_DAO.readByHotelId(hotelId);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        }
    }

    @Override
    public List<Room> getAllRooms() throws ServiceException {
        try {
            return ROOM_DAO.readAll();
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        }
    }



    @Override
    public List<Room> sortRoomsByRequest(Request request) throws ServiceException {
        try {
            List<Room> dbRoomList = ROOM_DAO.readAll();
            if (dbRoomList.size() != 0) {
                SortStep sortByDepartureDateStep = new SortRoomsByDepartureDateStep();
                SortStep sortByCheckInDateStep = new SortRoomsByCheckInDateStep(sortByDepartureDateStep);
                SortStep sortByCapacityStep = new SortRoomsByCapacityStep(sortByCheckInDateStep);
                SortStep sortByStarsAmountStep = new SortRoomsByStarsAmountStep(sortByCapacityStep);
                SortStep sortByAddressStep = new SortRoomsByAddressStep(sortByStarsAmountStep);
                sortByAddressStep.setRoomList(dbRoomList);
                return new ArrayList<>(sortByAddressStep.doSort(request));
            }
            return dbRoomList;
        } catch (DAOException | ChainException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        }
    }

    @Override
    public Room getRoomById(int id) throws ServiceException {
        try {
            return ROOM_DAO.readById(id);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server Error");
        }
    }

    @Override
    public void addHotelRoom(Room newRoom) throws ServiceException {
        try {
            ROOM_VALIDATOR.validate(newRoom);
            ROOM_DAO.add(newRoom);
        } catch (ValidatorException e) {
            LOGGER.warn(e);
            throw new ServiceException(e.getMessage());
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        }
    }
}
