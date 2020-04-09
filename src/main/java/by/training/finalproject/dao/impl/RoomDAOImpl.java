package by.training.finalproject.dao.impl;

import by.training.finalproject.builder.AddressBuilder;
import by.training.finalproject.builder.HotelBuilder;
import by.training.finalproject.builder.RoomBuilder;
import by.training.finalproject.dao.AbstractCommonDAO;
import by.training.finalproject.dao.SQLStatement;
import by.training.finalproject.dao.SQLTableLabel;
import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Hotel;
import by.training.finalproject.entity.Room;
import by.training.finalproject.entity.RoomStatus;
import by.training.finalproject.exception.DAOException;
import org.apache.log4j.Logger;

import java.awt.print.PrinterAbortException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl extends AbstractCommonDAO<Room> {

    private static final Logger LOGGER = Logger.getLogger(RoomDAOImpl.class);

    private RoomDAOImpl() {
    }

    public List<Room> readByRoomStatus(RoomStatus roomStatus) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_ROOM_LIST_BY_STATUS.getQuery());
            statement.setInt(1, roomStatus.getStatusId());
            return readListFromResultSet(statement);
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }


    public List<Room> readByCapacity(int capacity) throws DAOException {
        try (Connection connection = POOL.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_ROOM_LIST_BY_CAPACITY.getQuery());
            statement.setInt(1, capacity);
            return readListFromResultSet(statement);
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    protected PreparedStatement buildAddPreparedStatement(Connection connection, Room entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.ADD_ROOM.getQuery());
        setRoomDataFields(statement, entity);
        return statement;
    }

    @Override
    protected PreparedStatement buildUpdatePreparedStatement(Connection connection, Room entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.UPDATE_ROOM_BY_ID.getQuery());
        setRoomDataFields(statement, entity);
        statement.setInt(5, entity.getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildDeletePreparedStatement(Connection connection, Room entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.DELETE_ROOM_BY_ID.getQuery());
        statement.setInt(1, entity.getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildReadByIdPreparedStatement(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_ROOM_BY_ID.getQuery());
        statement.setInt(1, id);
        return statement;
    }

    @Override
    protected PreparedStatement buildReadAllPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(SQLStatement.GET_ROOM_LIST.getQuery());
    }

    @Override
    protected Room buildEntity(ResultSet resultSet) throws SQLException {
        RoomBuilder builder = new RoomBuilder(); // todo add builder factory
        builder.buildRoomId(resultSet.getInt(SQLTableLabel.ROOM_ID.getLabel()));
        builder.buildCapacity(resultSet.getInt(SQLTableLabel.ROOM_CAPACITY.getLabel()));
        builder.buildPrice(resultSet.getBigDecimal(SQLTableLabel.ROOM_PRICE.getLabel()));
        builder.buildStatus(RoomStatus.getStatusById(resultSet.getInt(SQLTableLabel.ROOM_STATUS_ID.getLabel())));
        builder.buildHotel(buildHotel(resultSet));
        return builder.build();
    }

    private Hotel buildHotel(ResultSet resultSet) throws SQLException {
        HotelBuilder builder = new HotelBuilder();
        builder.buildHotelId(resultSet.getInt(SQLTableLabel.HOTEL_ID.getLabel()));
        builder.buildName(resultSet.getString(SQLTableLabel.HOTEL_NAME.getLabel()));
        builder.buildStars(resultSet.getInt(SQLTableLabel.HOTEL_STARS.getLabel()));
        builder.buildAddress(buildAddress(resultSet));
        return builder.build();
    }

    private Address buildAddress(ResultSet resultSet) throws SQLException {
        AddressBuilder builder = new AddressBuilder();
        builder.buildAddressId(resultSet.getInt(SQLTableLabel.ADDRESS_ID.getLabel()));
        builder.buildCountry(resultSet.getString(SQLTableLabel.ADDRESS_COUNTRY.getLabel()));
        builder.buildTown(resultSet.getString(SQLTableLabel.ADDRESS_TOWN.getLabel()));
        return builder.build();
    }

    private void setRoomDataFields(PreparedStatement statement, Room entity) throws SQLException {
        int index = 0;
        statement.setInt(++index, entity.getCapacity());
        statement.setInt(++index, entity.getHotel().getId());
        statement.setDouble(++index, entity.getPrice().doubleValue());
        statement.setInt(++index, entity.getStatus().getStatusId());
    }

    private List<Room> readListFromResultSet(PreparedStatement statement) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                rooms.add(buildEntity(resultSet));
            }
            return rooms;
        }
    }
}
