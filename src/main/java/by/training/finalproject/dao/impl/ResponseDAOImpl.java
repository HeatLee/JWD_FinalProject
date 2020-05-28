package by.training.finalproject.dao.impl;

import by.training.finalproject.builder.*;
import by.training.finalproject.dao.AbstractCommonDAO;
import by.training.finalproject.dao.ResponseDAO;
import by.training.finalproject.dao.SQLStatement;
import by.training.finalproject.dao.SQLTableLabel;
import by.training.finalproject.entity.*;
import by.training.finalproject.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseDAOImpl extends AbstractCommonDAO<Response> implements ResponseDAO<Response> {
    private static final Logger LOGGER = Logger.getLogger(ResponseDAOImpl.class);
    private static final ResponseDAO<Response> INSTANCE = new ResponseDAOImpl();

    private ResponseDAOImpl() {
    }

    public static ResponseDAO<Response> getInstance() {
        return INSTANCE;
    }

    @Override
    public Response getResponseByRequestId(int id) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_RESPONSE_BY_REQUEST_ID.getQuery());
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return buildEntity(resultSet);
                }
            }
            return null;
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public Request getRequestByRoomId(int id) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_REQUEST_FROM_RESPONSE_BY_ROOM_ID.getQuery());
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return buildRequest(resultSet);
                }
            }
            return null;
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean isContainsRoom(int id) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_ROOM_FROM_RESPONSE_BY_ID.getQuery());
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException();
        }
    }

    @Override
    public void add(Response response) throws DAOException {
        Connection connection = POOL.getConnection();
        try {
            PreparedStatement addResponseStatement = buildAddPreparedStatement(connection, response);
            PreparedStatement updateRequestStatusStatement = buildUpdateRequestStatusStatement(connection,
                    response.getRequest().getId(), RequestStatus.APPROVED);
            PreparedStatement updateRoomStatusStatement = buildUpdateRoomStatusStatement(connection,
                    response.getRoom().getId(), RoomStatus.RESERVED);
            connection.setAutoCommit(false);
            addResponseStatement.executeUpdate();
            updateRequestStatusStatement.executeUpdate();
            updateRoomStatusStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollbackTransaction(connection);
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Response response) throws DAOException {
        Connection connection = POOL.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement deleteResponseStatement = buildDeletePreparedStatement(connection, response);
            PreparedStatement deleteRequestStatement = buildDeleteRequestStatement(connection,
                    response.getRequest().getId());
            PreparedStatement updateRoomStatusStatement = buildUpdateRoomStatusStatement(connection,
                    response.getRoom().getId(), RoomStatus.AVAILABLE);
            deleteResponseStatement.executeUpdate();
            updateRoomStatusStatement.executeUpdate();
            deleteRequestStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollbackTransaction(connection);
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeConnection(connection);
        }
    }

    private PreparedStatement buildDeleteRequestStatement(Connection connection,
                                                          int requestId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.DELETE_REQUEST.getQuery());
        statement.setInt(1, requestId);
        return statement;
    }

    private PreparedStatement buildUpdateRoomStatusStatement(Connection connection,
                                                             int roomId,
                                                             RoomStatus newStatus) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.UPDATE_ROOM_STATUS.getQuery());
        int index = 0;
        statement.setInt(++index, newStatus.getStatusId());
        statement.setInt(++index, roomId);
        return statement;
    }

    private PreparedStatement buildUpdateRequestStatusStatement(Connection connection,
                                                                int requestId,
                                                                RequestStatus newStatus) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.UPDATE_REQUEST_STATUS.getQuery());
        int index = 0;
        statement.setInt(++index, newStatus.getId());
        statement.setInt(++index, requestId);
        return statement;
    }

    @Override
    protected PreparedStatement buildAddPreparedStatement(Connection connection, Response entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.ADD_NEW_RESPONSE.getQuery());
        int index = 0;
        statement.setInt(++index, entity.getRequest().getId());
        statement.setInt(++index, entity.getRoom().getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildUpdatePreparedStatement(Connection connection, Response entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.UPDATE_RESPONSE_BY_ID.getQuery());
        int index = 0;
        statement.setInt(++index, entity.getRequest().getId());
        statement.setInt(++index, entity.getRoom().getId());
        statement.setInt(++index, entity.getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildDeletePreparedStatement(Connection connection, Response entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.DELETE_RESPONSE_BY_ID.getQuery());
        statement.setInt(1, entity.getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildReadByIdPreparedStatement(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_RESPONSE_BY_ID.getQuery());
        statement.setInt(1, id);
        return statement;
    }

    @Override
    protected PreparedStatement buildReadAllPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(SQLStatement.GET_ALL_RESPONSES.getQuery());
    }

    private Address buildAddress(ResultSet resultSet) throws SQLException {
        return new AddressBuilder()
                .buildAddressId(resultSet.getInt(SQLTableLabel.ADDRESS_ID.getLabel()))
                .buildCountry(resultSet.getString(SQLTableLabel.ADDRESS_COUNTRY.getLabel()))
                .buildTown(resultSet.getString(SQLTableLabel.ADDRESS_TOWN.getLabel()))
                .build();
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new UserBuilder()
                .buildLogin(resultSet.getString(SQLTableLabel.USER_LOGIN.getLabel()))
                .buildPassword(resultSet.getString(SQLTableLabel.USER_PASSWORD.getLabel()))
                .buildEmail(resultSet.getString(SQLTableLabel.USER_EMAIL.getLabel()))
                .buildRole(UserRole.getRoleById(resultSet.getInt(SQLTableLabel.USER_ROLE_ID.getLabel())))
                .buildUserId(resultSet.getInt(SQLTableLabel.USER_ID.getLabel()))
                .build();
    }

    private Request buildRequest(ResultSet resultSet) throws SQLException {
        return new RequestBuilder()
                .buildAddress(buildAddress(resultSet))
                .buildStars(resultSet.getInt(SQLTableLabel.REQUEST_STARS.getLabel()))
                .buildCheckInDate(resultSet.getDate(SQLTableLabel.REQUEST_CHECK_IN.getLabel()).toLocalDate())
                .buildDepartureDate(resultSet.getDate(SQLTableLabel.REQUEST_DEPARTURE.getLabel()).toLocalDate())
                .buildCapacity(resultSet.getInt(SQLTableLabel.REQUEST_CAPACITY.getLabel()))
                .buildRequestId(resultSet.getInt(SQLTableLabel.REQUEST_ID.getLabel()))
                .buildReservationUser(buildUser(resultSet))
                .buildStatus(resultSet.getInt(SQLTableLabel.REQUEST_STATUS_ID.getLabel()))
                .build();
    }

    private Hotel buildHotel(ResultSet resultSet) throws SQLException {
        return new HotelBuilder()
                .buildHotelId(resultSet.getInt(SQLTableLabel.HOTEL_ID.getLabel()))
                .buildName(resultSet.getString(SQLTableLabel.HOTEL_NAME.getLabel()))
                .buildStars(resultSet.getInt(SQLTableLabel.HOTEL_STARS.getLabel()))
                .buildAddress(buildAddress(resultSet))
                .build();
    }

    private Room buildRoom(ResultSet resultSet) throws SQLException {
        return new RoomBuilder()
                .buildRoomId(resultSet.getInt(SQLTableLabel.ROOM_ID.getLabel()))
                .buildCapacity(resultSet.getInt(SQLTableLabel.ROOM_CAPACITY.getLabel()))
                .buildPrice(resultSet.getBigDecimal(SQLTableLabel.ROOM_PRICE.getLabel()))
                .buildStatus(RoomStatus.getStatusById(resultSet.getInt(SQLTableLabel.ROOM_STATUS_ID.getLabel())))
                .buildHotel(buildHotel(resultSet))
                .build();
    }

    @Override
    protected Response buildEntity(ResultSet resultSet) throws SQLException {
        return new ResponseBuilder()
                .buildId(resultSet.getInt(SQLTableLabel.RESPONSE_ID.getLabel()))
                .buildRequest(buildRequest(resultSet))
                .buildRoom(buildRoom(resultSet))
                .build();
    }
}
