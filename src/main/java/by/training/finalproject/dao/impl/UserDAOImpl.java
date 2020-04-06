package by.training.finalproject.dao.impl;

import by.training.finalproject.builder.UserBuilder;
import by.training.finalproject.dao.SQLStatement;
import by.training.finalproject.dao.UserDAO;
import by.training.finalproject.entity.User;
import by.training.finalproject.entity.UserRole;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.pool.ConnectionPool;
import by.training.finalproject.pool.ProxyConnection;
import org.apache.log4j.Logger;

import javax.management.relation.RelationSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
    private static final ConnectionPool POOL = ConnectionPool.INSTANCE;

    private static final String LOGIN_TABLE_LABEL = "login" ;
    private static final String PASSWORD_TABLE_LABEL = "password" ;
    private static final String EMAIL_TABLE_LABEL = "email" ;
    private static final String USER_ROLE_ID_TABLE_LABEL = "user_role_id" ;
    private static final String USER_ID_TABLE_LABEL = "user_id";

    private UserDAOImpl() {
    }

    @Override
    public void add(User user) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.ADD_USER.getQuery());
            int statementIndex = 0;
            statement.setString(++statementIndex, user.getLogin());
            statement.setString(++statementIndex, user.getPassword());
            statement.setString(++statementIndex, user.getEmail());
            statement.setInt(++statementIndex, user.getUserRole().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.UPDATE_USER_BY_ID.getQuery());
            int statementIndex = 0;
            statement.setString(++statementIndex, user.getLogin());
            statement.setString(++statementIndex, user.getPassword());
            statement.setString(++statementIndex, user.getEmail());
            statement.setInt(++statementIndex, user.getUserRole().getId());
            statement.setInt(++statementIndex, user.getUserId());
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(User user) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.DELETE_USER_BY_ID.getQuery());
            statement.setInt(1, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public User readById(int id) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_USER_BY_ID.getQuery());
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return buildUser(resultSet);
                }
                return null;
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> readAll() throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_USER_LIST.getQuery());
            try (ResultSet resultSet = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(buildUser(resultSet));
                }
                return users;
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public User readByLogin(String login) throws DAOException{
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_USER_BY_LOGIN.getQuery());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return buildUser(resultSet);
                }
                return null;
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public User readByEmail(String email) throws DAOException{
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLStatement.GET_USER_BY_EMAIL.getQuery());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return buildUser(resultSet);
                }
                return null;
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException{
        UserBuilder builder = new UserBuilder();
        builder.buildLogin(resultSet.getString(LOGIN_TABLE_LABEL));
        builder.buildPassword(resultSet.getString(PASSWORD_TABLE_LABEL));
        builder.buildEmail(resultSet.getString(EMAIL_TABLE_LABEL));
        builder.buildRole(UserRole.getRoleById(resultSet.getInt(USER_ROLE_ID_TABLE_LABEL)));
        builder.buildUserId(resultSet.getInt(USER_ID_TABLE_LABEL));
        return builder.build();
    }

}
