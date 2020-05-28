package by.training.finalproject.dao.impl;

import by.training.finalproject.builder.AddressBuilder;
import by.training.finalproject.dao.AbstractCommonDAO;
import by.training.finalproject.dao.AddressDAO;
import by.training.finalproject.dao.SQLStatement;
import by.training.finalproject.dao.SQLTableLabel;
import by.training.finalproject.entity.Address;
import by.training.finalproject.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAOImpl extends AbstractCommonDAO<Address> implements AddressDAO<Address> {
    private static final Logger LOGGER = Logger.getLogger(AddressDAOImpl.class);
    private static final AddressDAO<Address> INSTANCE = new AddressDAOImpl();

    private AddressDAOImpl() {
    }

    public static AddressDAO<Address> getInstance() {
        return INSTANCE;
    }

    @Override
    public Address getAddressByFullData(String country, String town) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_ADDRESS_BY_DATA.getQuery());
            int index = 0;
            statement.setString(++index, country);
            statement.setString(++index, town);
            try (ResultSet resultSet = statement.executeQuery()){
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
    protected PreparedStatement buildAddPreparedStatement(Connection connection, Address entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.ADD_ADDRESS.getQuery());
        int index = 0;
        statement.setString(++index, entity.getCountry());
        statement.setString(++index, entity.getTown());
        return statement;
    }

    @Override
    protected PreparedStatement buildUpdatePreparedStatement(Connection connection, Address entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.UPDATE_ADDRESS.getQuery());
        int index = 0;
        statement.setString(++index, entity.getCountry());
        statement.setString(++index, entity.getTown());
        statement.setInt(++index, entity.getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildDeletePreparedStatement(Connection connection, Address entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.DELETE_ADDRESS.getQuery());
        statement.setInt(1, entity.getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildReadByIdPreparedStatement(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_ADDRESS_BY_ID.getQuery());
        statement.setInt(1, id);
        return statement;
    }

    @Override
    protected PreparedStatement buildReadAllPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(SQLStatement.GET_ADDRESS_LIST.getQuery());
    }

    @Override
    protected Address buildEntity(ResultSet resultSet) throws SQLException {
        return new AddressBuilder()
                .buildAddressId(resultSet.getInt(SQLTableLabel.ADDRESS_ID.getLabel()))
                .buildCountry(resultSet.getString(SQLTableLabel.ADDRESS_COUNTRY.getLabel()))
                .buildTown(resultSet.getString(SQLTableLabel.ADDRESS_TOWN.getLabel()))
                .build();
    }
}
