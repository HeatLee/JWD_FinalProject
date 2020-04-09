package by.training.finalproject.service;

import by.training.finalproject.exception.ServiceException;

import java.util.List;

public interface CommonService<T>{
    T add(T t) throws ServiceException;

    T update(T t);

    T delete(T t);

    T getById(int id);

    List<T> getAll();
}
