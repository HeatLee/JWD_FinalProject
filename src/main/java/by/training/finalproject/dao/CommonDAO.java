package by.training.finalproject.dao;

import by.training.finalproject.exception.DAOException;

import java.util.List;

/**
 * <p>
 *     An interface that provides access to the core functionality of the data access layer.
 * </p>
 *
 * @param <T> the type of entity DAO working with
 */
public interface CommonDAO <T>{

    /**
     * Method for adding a new class T object to the data store
     * @param t that should be added
     * @throws DAOException if something went wrong while adding new entity
     */
    void add(T t) throws DAOException;

    /**
     * Method for updating entity stat, stored in data store
     * @param t updated entity
     * @throws DAOException if something went wrong while updating entity
     */
    void update(T t) throws DAOException;

    /**
     * Method for deleting entity from data store
     * @param t is entity, that should be deleted
     * @throws DAOException if something goes wrong while deleting entity
     */
    void delete(T t) throws DAOException;

    /**
     * Method reads entity from data store with specific id
     * @param id is method's id
     * @return The complete entity that was obtained from the data warehouse
     * @throws DAOException thrown if something goes wrong while reading entity
     */
    T readById(int id) throws DAOException;

    /**
     * Method reads all entities from data store
     * @return List<T> of entities with type T
     * @throws DAOException thrown when something goes wrong while reading entities
     */
    List<T> readAll() throws DAOException;

}
