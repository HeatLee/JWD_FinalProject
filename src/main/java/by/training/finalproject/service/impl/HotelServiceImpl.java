package by.training.finalproject.service.impl;

import by.training.finalproject.dao.AddressDAO;
import by.training.finalproject.dao.HotelDAO;
import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Hotel;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.factory.DAOFactory;
import by.training.finalproject.service.HotelService;
import by.training.finalproject.validator.Validator;
import by.training.finalproject.validator.impl.HotelValidator;
import org.apache.log4j.Logger;

import java.util.List;


public class HotelServiceImpl implements HotelService {
    private static final Logger LOGGER = Logger.getLogger(HotelServiceImpl.class);
    private static final HotelDAO<Hotel> HOTEL_DAO = DAOFactory.INSTANCE.getHotelDAO();
    private static final AddressDAO<Address> ADDRESS_DAO = DAOFactory.INSTANCE.getAddressDAO();
    private static final Validator<Hotel> HOTEL_VALIDATOR = new HotelValidator();
    private static final HotelService INSTANCE = new HotelServiceImpl();

    private HotelServiceImpl() {
    }

    public static HotelService getInstance() {
        return INSTANCE;
    }

    @Override
    public Hotel getHotelById(int hotelId) throws ServiceException {
        try {
            Hotel hotel = HOTEL_DAO.readById(hotelId);
            if (hotel == null) {
                throw new ServiceException("No such hotel");
            }
            return hotel;
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        }
    }

    @Override
    public void addHotel(Hotel hotel) throws ServiceException {
        try {
            HOTEL_VALIDATOR.validate(hotel);
            Address dbAddress = ADDRESS_DAO.getAddressByFullData(hotel.getAddress().getCountry(),
                    hotel.getAddress().getTown());
            if (HOTEL_DAO.findHotelByName(hotel.getName()) != null
                    && dbAddress != null) {
                throw new ServiceException("Such hotel already exist in database");
            }
            if (dbAddress == null) {
                HOTEL_DAO.addWithAddress(hotel);
            } else {
                HOTEL_DAO.add(hotel);
            }
        } catch (ValidatorException e) {
            LOGGER.warn(e);
            throw new ServiceException(e.getMessage());
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        }
    }

    @Override
    public List<Hotel> getAllHotels() throws ServiceException {
        try {
            return HOTEL_DAO.readAll();
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error");
        }
    }
}
