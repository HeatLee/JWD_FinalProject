package by.training.finalproject.dao;

import by.training.finalproject.exception.DAOException;

import java.util.List;

public interface CommonDAO <T>{

    void add(T t) throws DAOException;

    void update(T t) throws DAOException;

    void delete(T t) throws DAOException;

    T readById(int id) throws DAOException;

    List<T> readAll() throws DAOException;

}
