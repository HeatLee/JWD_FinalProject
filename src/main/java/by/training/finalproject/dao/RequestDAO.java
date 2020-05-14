package by.training.finalproject.dao;

import by.training.finalproject.entity.Request;
import by.training.finalproject.exception.DAOException;

import java.util.List;

public interface RequestDAO<T> extends CommonDAO<T> {
    List<Request> readRequestsByUserId(int userId) throws DAOException;
}
