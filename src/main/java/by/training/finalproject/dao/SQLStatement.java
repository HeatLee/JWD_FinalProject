package by.training.finalproject.dao;

public enum SQLStatement {
    ADD_USER("INSERT INTO reservation_user (login, password, email, user_role_id) " +
            "VALUES (?, ?, ?, ?)"),
    DELETE_USER_BY_ID("DELETE FROM reservation_user " +
            "WHERE user_id = ?"),
    UPDATE_USER_BY_ID("UPDATE reservation_user t " +
            "SET t.login = ?, t.password = ?, t.email = ?, t.user_role_id = ? " +
            "WHERE t.user_id = ?"),
    GET_USER_BY_ID("SELECT user_id, login, password, email, user_role_id " +
            "FROM reservation_user " +
            "WHERE user_id = ?"),
    GET_USER_LIST("SELECT user_id, login, password, email, user_role_id " +
            "FROM reservation_user"),
    GET_USER_BY_EMAIL("SELECT user_id, login, password, email, user_role_id " +
            "FROM reservation_user " +
            "WHERE email = ?"),
    GET_USER_BY_LOGIN("SELECT user_id, login, password, email, user_role_id " +
            "FROM reservation_user " +
            "WHERE login = ?"),
    GET_PASSWORD_BY_LOGIN("SELECT password\n" +
            "from bd_jwd_reservation.reservation_user\n" +
            "WHERE login = ?"),
    ADD_ROOM("INSERT INTO hotel_room (room_capacity, hotel_id, room_price, room_status_id)" +
            " VALUES (?, ?, ?, ?)"),
    UPDATE_ROOM_BY_ID("UPDATE hotel_room t " +
            "SET t.room_capacity = ?, t.hotel_id = ?, t.room_price = ? , t.room_status_id = ? " +
            "WHERE t.hotel_room_id = ?"),
    DELETE_ROOM_BY_ID("DELETE FROM hotel_room " +
            "WHERE hotel_room_id = ?"),
    GET_ROOM_BY_ID("SELECT hotel_room.hotel_room_id,\n" +
            "       hotel_room.room_capacity,\n" +
            "       hotel_room.room_price,\n" +
            "       hotel_room.room_status_id,\n" +
            "       hotel.hotel_id,\n" +
            "       hotel.hotel_name,\n" +
            "       hotel.stars,\n" +
            "       address.address_id,\n" +
            "       address.country,\n" +
            "       address.town\n" +
            "FROM hotel_room,\n" +
            "     hotel,\n" +
            "     address\n" +
            "WHERE hotel_room.hotel_id = hotel.hotel_id\n" +
            "  AND hotel.address_id = address.address_id\n" +
            "  AND hotel_room.hotel_room_id = ?"),
    GET_ROOM_LIST("SELECT hotel_room.hotel_room_id,\n" +
            "       hotel_room.room_capacity,\n" +
            "       hotel_room.room_price,\n" +
            "       hotel_room.room_status_id,\n" +
            "       hotel.hotel_id,\n" +
            "       hotel.hotel_name,\n" +
            "       hotel.stars,\n" +
            "       address.address_id,\n" +
            "       address.country,\n" +
            "       address.town\n" +
            "FROM hotel_room,\n" +
            "     hotel,\n" +
            "     address\n" +
            "WHERE hotel_room.hotel_id = hotel.hotel_id\n" +
            "  AND hotel.address_id = address.address_id"),
    GET_ROOM_LIST_BY_STATUS("SELECT hotel_room.hotel_room_id,\n" +
            "       hotel_room.room_capacity,\n" +
            "       hotel_room.room_price,\n" +
            "       hotel_room.room_status_id,\n" +
            "       hotel.hotel_id,\n" +
            "       hotel.hotel_name,\n" +
            "       hotel.stars,\n" +
            "       address.address_id,\n" +
            "       address.country,\n" +
            "       address.town\n" +
            "FROM hotel_room,\n" +
            "     hotel,\n" +
            "     address\n" +
            "WHERE hotel_room.hotel_id = hotel.hotel_id\n" +
            "  AND hotel.address_id = address.address_id\n" +
            "  AND hotel_room.room_status_id = ?"),
    GET_ROOM_LIST_BY_CAPACITY("SELECT hotel_room.hotel_room_id,\n" +
            "       hotel_room.room_capacity,\n" +
            "       hotel_room.room_price,\n" +
            "       hotel_room.room_status_id,\n" +
            "       hotel.hotel_id,\n" +
            "       hotel.hotel_name,\n" +
            "       hotel.stars,\n" +
            "       address.address_id,\n" +
            "       address.country,\n" +
            "       address.town\n" +
            "FROM hotel_room,\n" +
            "     hotel,\n" +
            "     address\n" +
            "WHERE hotel_room.hotel_id = hotel.hotel_id\n" +
            "  AND hotel.address_id = address.address_id\n" +
            "  AND hotel_room.room_capacity = ?"),
    ADD_REQUEST("INSERT INTO" +
            " bd_jwd_reservation.request" +
            " (capacity, check_in, departure, stars, reservation_user_id, address_id, status_id)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)"),
    UPDATE_REQUEST("UPDATE bd_jwd_reservation.request t " +
            "SET" +
            " t.status_id = ?" +
            " t.capacity = ?" +
            " t.check_in = ?" +
            " t.departure = ?" +
            " t.stars = ?" +
            " t.reservation_user_id = ?" +
            " t.address_id = ?" +
            "WHERE t.request_id = ?"),
    DELETE_REQUEST("DELETE FROM bd_jwd_reservation.request t " +
            "WHERE t.request_id = ?"),
    GET_REQUEST_BY_ID("SELECT request.request_id,\n" +
            "       request.capacity,\n" +
            "       request.check_in,\n" +
            "       request.departure,\n" +
            "       request.stars,\n" +
            "       request.status_id,\n" +
            "       reservation_user.user_id,\n" +
            "       reservation_user.email,\n" +
            "       reservation_user.password,\n" +
            "       reservation_user.login,\n" +
            "       reservation_user.user_role_id,\n" +
            "       address.address_id,\n" +
            "       address.town,\n" +
            "       address.country\n" +
            "FROM request,\n" +
            "     address,\n" +
            "     reservation_user\n" +
            "WHERE request.address_id = address.address_id\n" +
            "  AND request.reservation_user_id = reservation_user.user_id\n" +
            "  AND request.request_id = ?"),
    GET_REQUEST_BY_USER_ID("SELECT request.request_id,\n" +
            "       request.capacity,\n" +
            "       request.check_in,\n" +
            "       request.departure,\n" +
            "       request.stars,\n" +
            "       request.status_id,\n" +
            "       reservation_user.user_id,\n" +
            "       reservation_user.email,\n" +
            "       reservation_user.password,\n" +
            "       reservation_user.login,\n" +
            "       reservation_user.user_role_id,\n" +
            "       address.address_id,\n" +
            "       address.town,\n" +
            "       address.country\n" +
            "FROM request,\n" +
            "     address,\n" +
            "     reservation_user\n" +
            "WHERE request.address_id = address.address_id\n" +
            "  AND request.reservation_user_id = reservation_user.user_id\n" +
            "  AND request.reservation_user_id = ?"),
    GET_REQUEST_LIST("SELECT request.request_id,\n" +
            "       request.capacity,\n" +
            "       request.check_in,\n" +
            "       request.departure,\n" +
            "       request.stars,\n" +
            "       request.status_id,\n" +
            "       reservation_user.user_id,\n" +
            "       reservation_user.email,\n" +
            "       reservation_user.password,\n" +
            "       reservation_user.login,\n" +
            "       reservation_user.user_role_id,\n" +
            "       address.address_id,\n" +
            "       address.town,\n" +
            "       address.country\n" +
            "FROM request,\n" +
            "     address,\n" +
            "     reservation_user\n" +
            "WHERE request.address_id = address.address_id\n" +
            "  AND request.reservation_user_id = reservation_user.user_id"),
    ADD_ADDRESS("INSERT INTO bd_jwd_reservation.address (country, town) " +
            "VALUES (?, ?)"),
    UPDATE_ADDRESS("UPDATE bd_jwd_reservation.address t " +
            "SET t.country = ?, t.town = ? " +
            "WHERE t.address_id = ?"),
    DELETE_ADDRESS("DELETE FROM bd_jwd_reservation.address " +
            "WHERE address_id = ?"),
    GET_ADDRESS_BY_ID("SELECT country,\n" +
            "       town,\n" +
            "       address_id\n" +
            "FROM bd_jwd_reservation.address\n" +
            "WHERE address_id = ?"),
    GET_ADDRESS_LIST("SELECT country,\n" +
            "       town,\n" +
            "       address_id\n" +
            "FROM bd_jwd_reservation.address"),
    GET_ADDRESS_BY_DATA("SELECT country,\n" +
            "       town,\n" +
            "       address_id\n" +
            "FROM address\n" +
            "WHERE country = ? AND town = ?");

    private String query;

    SQLStatement(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
