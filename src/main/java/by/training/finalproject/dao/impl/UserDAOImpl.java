package by.training.finalproject.dao.impl;

import by.training.finalproject.builder.UserBuilder;
import by.training.finalproject.dao.AbstractCommonDAO;
import by.training.finalproject.dao.SQLStatement;
import by.training.finalproject.dao.SQLTableLabel;
import by.training.finalproject.entity.User;
import by.training.finalproject.entity.UserRole;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends AbstractCommonDAO<User> {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
    private static final ConnectionPool POOL = ConnectionPool.INSTANCE;

    private UserDAOImpl() {
    }

    public User readByLogin(String login) throws DAOException{
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_USER_BY_LOGIN.getQuery());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return buildEntity(resultSet);
                }
                return null;
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    public User readByEmail(String email) throws DAOException{
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_USER_BY_EMAIL.getQuery());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return buildEntity(resultSet);
                }
                return null;
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    protected PreparedStatement buildAddPreparedStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.ADD_USER.getQuery());
        int statementIndex = 0;
        statement.setString(++statementIndex, entity.getLogin());
        statement.setString(++statementIndex, entity.getPassword());
        statement.setString(++statementIndex, entity.getEmail());
        statement.setInt(++statementIndex, entity.getUserRole().getId());
        return statement;
    }

    @Override
    protected PreparedStatement buildUpdatePreparedStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.UPDATE_USER_BY_ID.getQuery());
        int statementIndex = 0;
        statement.setString(++statementIndex, entity.getLogin());
        statement.setString(++statementIndex, entity.getPassword());
        statement.setString(++statementIndex, entity.getEmail());
        statement.setInt(++statementIndex, entity.getUserRole().getId());
        statement.setInt(++statementIndex, entity.getUserId());
        return statement;
    }

    @Override
    protected PreparedStatement buildDeletePreparedStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.DELETE_USER_BY_ID.getQuery());
        statement.setInt(1, entity.getUserId());
        return statement;
    }

    @Override
    protected PreparedStatement buildReadByIdPreparedStatement(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_USER_BY_ID.getQuery());
        statement.setInt(1, id);
        return statement;
    }

    @Override
    protected PreparedStatement buildReadAllPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(SQLStatement.GET_USER_LIST.getQuery());
    }

    @Override
    protected User buildEntity(ResultSet resultSet) throws SQLException {
        UserBuilder builder = new UserBuilder(); //todo add factory for builder
        builder.buildLogin(resultSet.getString(SQLTableLabel.USER_LOGIN.getLabel()));
        builder.buildPassword(resultSet.getString(SQLTableLabel.USER_PASSWORD.getLabel()));
        builder.buildEmail(resultSet.getString(SQLTableLabel.USER_EMAIL.getLabel()));
        builder.buildRole(UserRole.getRoleById(resultSet.getInt(SQLTableLabel.USER_ROLE_ID.getLabel())));
        builder.buildUserId(resultSet.getInt(SQLTableLabel.USER_ID.getLabel()));
        return builder.build();
    }
}
