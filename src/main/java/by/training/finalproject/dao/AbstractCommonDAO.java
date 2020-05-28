package by.training.finalproject.dao;

import by.training.finalproject.exception.DAOException;
import by.training.finalproject.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommonDAO<T> {
    protected final static ConnectionPool POOL = ConnectionPool.INSTANCE;
    private final static Logger LOGGER = Logger.getLogger(AbstractCommonDAO.class);


    public void add(T t) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = buildAddPreparedStatement(connection, t);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }


    public void update(T t) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = buildUpdatePreparedStatement(connection, t);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    public void delete(T t) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = buildDeletePreparedStatement(connection, t);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }


    public T readById(int id) throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = buildReadByIdPreparedStatement(connection, id);
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


    public List<T> readAll() throws DAOException {
        try (Connection connection = POOL.getConnection()) {
            PreparedStatement statement = buildReadAllPreparedStatement(connection);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<T> tList = new ArrayList<>();
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

    protected void closeConnection(Connection connection) throws DAOException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new DAOException(e);
            }
        }
    }

    protected void rollbackTransaction(Connection connection) throws DAOException {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new DAOException(e);
            }
        }
    }

    protected abstract PreparedStatement buildAddPreparedStatement(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement buildUpdatePreparedStatement(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement buildDeletePreparedStatement(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement buildReadByIdPreparedStatement(Connection connection, int id) throws SQLException;

    protected abstract PreparedStatement buildReadAllPreparedStatement(Connection connection) throws SQLException;

    protected abstract T buildEntity(ResultSet resultSet) throws SQLException;
}
