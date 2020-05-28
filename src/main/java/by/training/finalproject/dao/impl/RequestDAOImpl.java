package by.training.finalproject.dao.impl;

import by.training.finalproject.builder.AddressBuilder;
import by.training.finalproject.builder.RequestBuilder;
import by.training.finalproject.builder.UserBuilder;
import by.training.finalproject.dao.AbstractCommonDAO;
import by.training.finalproject.dao.RequestDAO;
import by.training.finalproject.dao.SQLStatement;
import by.training.finalproject.dao.SQLTableLabel;
import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.User;
import by.training.finalproject.entity.UserRole;
import by.training.finalproject.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAOImpl extends AbstractCommonDAO<Request> implements RequestDAO<Request> {
    private static final Logger LOGGER = Logger.getLogger(RequestDAOImpl.class);
    private static final RequestDAOImpl INSTANCE = new RequestDAOImpl();

    private RequestDAOImpl() {
    }

    public static RequestDAOImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Request> readRequestsByUserId(int userId) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_REQUEST_BY_USER_ID.getQuery());
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Request> tList = new ArrayList<>();
                while (resultSet.next()) {
                    tList.add(buildEntity(resultSet));
                }
                return tList;
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    protected PreparedStatement buildAddPreparedStatement(Connection connection, Request entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.ADD_REQUEST.getQuery());
        int index = 0;
        statement.setInt(++index, entity.getCapacity());
        statement.setDate(++index, Date.valueOf(entity.getCheckIn()));
        statement.setDate(++index, Date.valueOf(entity.getDeparture()));
        statement.setInt(++index, entity.getStars());
        statement.setInt(++index, entity.getReservationUser().getUserId());
        statement.setInt(++index, entity.getAddress().getId());
        statement.setInt(++index, entity.getStatus().getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildUpdatePreparedStatement(Connection connection, Request entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.UPDATE_REQUEST.getQuery());
        int index = 0;
        statement.setInt(++index, entity.getStatus().getId());
        statement.setInt(++index, entity.getCapacity());
        statement.setDate(++index, Date.valueOf(entity.getCheckIn()));
        statement.setDate(++index, Date.valueOf(entity.getDeparture()));
        statement.setInt(++index, entity.getStars());
        statement.setInt(++index, entity.getReservationUser().getUserId());
        statement.setInt(++index, entity.getAddress().getId());
        statement.setInt(++index, entity.getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildDeletePreparedStatement(Connection connection, Request entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.DELETE_REQUEST.getQuery());
        statement.setInt(1, entity.getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildReadByIdPreparedStatement(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_REQUEST_BY_ID.getQuery());
        statement.setInt(1, id);
        return statement;
    }

    @Override
    protected PreparedStatement buildReadAllPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(SQLStatement.GET_REQUEST_LIST.getQuery());
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

    @Override
    protected Request buildEntity(ResultSet resultSet) throws SQLException {
        return new RequestBuilder()
                .buildAddress(buildAddress(resultSet))
                .buildStars(resultSet.getInt(SQLTableLabel.REQUEST_STARS.getLabel()))
                .buildCheckInDate(resultSet.getDate(SQLTableLabel.REQUEST_CHECK_IN.getLabel()).toLocalDate())
                .buildDepartureDate(resultSet.getDate(SQLTableLabel.REQUEST_DEPARTURE.getLabel()).toLocalDate())
                .buildCapacity(resultSet.getInt(SQLTableLabel.REQUEST_CAPACITY.getLabel()))
                .buildRequestId(resultSet.getInt(SQLTableLabel.REQUEST_ID.getLabel()))
                .buildStatus(resultSet.getInt(SQLTableLabel.REQUEST_STATUS_ID.getLabel()))
                .buildReservationUser(buildUser(resultSet))
                .build();
    }

}
