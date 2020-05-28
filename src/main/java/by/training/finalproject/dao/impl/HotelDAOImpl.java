package by.training.finalproject.dao.impl;

import by.training.finalproject.builder.AddressBuilder;
import by.training.finalproject.builder.HotelBuilder;
import by.training.finalproject.dao.AbstractCommonDAO;
import by.training.finalproject.dao.HotelDAO;
import by.training.finalproject.dao.SQLStatement;
import by.training.finalproject.dao.SQLTableLabel;
import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Hotel;
import by.training.finalproject.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelDAOImpl extends AbstractCommonDAO<Hotel> implements HotelDAO<Hotel> {
    private static final Logger LOGGER = Logger.getLogger(HotelDAOImpl.class);
    private static final HotelDAO<Hotel> INSTANCE = new HotelDAOImpl();

    private HotelDAOImpl() {
    }

    public static HotelDAO<Hotel> getInstance() {
        return INSTANCE;
    }

    @Override
    public void addWithAddress(Hotel hotel) throws DAOException {
        Connection connection = POOL.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement addAddressStatement = buildAddAddressStatement(connection, hotel.getAddress());
            addAddressStatement.executeUpdate();
            ResultSet resultSet = addAddressStatement.getGeneratedKeys();
            if (resultSet.next()) {
                hotel.getAddress().setId(resultSet.getInt(1));
            }
            PreparedStatement addHotelStatement = buildAddPreparedStatement(connection, hotel);
            addHotelStatement.executeUpdate();
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
    public Hotel findHotelByName(String name) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_HOTEL_BY_NAME.getQuery());
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return buildEntity(resultSet);
            }
            return null;
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    private PreparedStatement buildAddAddressStatement(Connection connection, Address address) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.ADD_ADDRESS.getQuery(),
                PreparedStatement.RETURN_GENERATED_KEYS);
        int index = 0;
        statement.setString(++index, address.getCountry());
        statement.setString(++index, address.getTown());
        return statement;
    }

    @Override
    protected PreparedStatement buildAddPreparedStatement(Connection connection, Hotel entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.ADD_HOTEL.getQuery());
        int index = 0;
        statement.setString(++index, entity.getName());
        statement.setInt(++index, entity.getStars());
        statement.setInt(++index, entity.getAddress().getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildUpdatePreparedStatement(Connection connection, Hotel entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.UPDATE_HOTEL.getQuery());
        int index = 0;
        statement.setString(++index, entity.getName());
        statement.setInt(++index, entity.getStars());
        statement.setInt(++index, entity.getAddress().getId());
        statement.setInt(++index, entity.getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildDeletePreparedStatement(Connection connection, Hotel entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.DELETE_HOTEL_BY_ID.getQuery());
        statement.setInt(1, entity.getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildReadByIdPreparedStatement(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_HOTEL_BY_ID.getQuery());
        statement.setInt(1, id);
        return statement;
    }

    @Override
    protected PreparedStatement buildReadAllPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(SQLStatement.GET_ALL_HOTELS.getQuery());
    }

    private Address buildAddress(ResultSet resultSet) throws SQLException {
        return new AddressBuilder()
                .buildAddressId(resultSet.getInt(SQLTableLabel.ADDRESS_ID.getLabel()))
                .buildCountry(resultSet.getString(SQLTableLabel.ADDRESS_COUNTRY.getLabel()))
                .buildTown(resultSet.getString(SQLTableLabel.ADDRESS_TOWN.getLabel()))
                .build();
    }

    @Override
    protected Hotel buildEntity(ResultSet resultSet) throws SQLException {
        return new HotelBuilder()
                .buildHotelId(resultSet.getInt(SQLTableLabel.HOTEL_ID.getLabel()))
                .buildName(resultSet.getString(SQLTableLabel.HOTEL_NAME.getLabel()))
                .buildStars(resultSet.getInt(SQLTableLabel.HOTEL_STARS.getLabel()))
                .buildAddress(buildAddress(resultSet))
                .build();
    }
}
