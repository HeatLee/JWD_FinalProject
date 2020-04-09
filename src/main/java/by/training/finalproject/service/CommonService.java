package by.training.finalproject.service;

import by.training.finalproject.exception.ServiceException;

import java.util.List;

public interface CommonService<T>{
    T add(T t) throws ServiceException;

    T update(T t) throws ServiceException;

    T delete(T t) throws ServiceException;

    T getById(int id) throws ServiceException;

    List<T> getAll() throws ServiceException;
}
