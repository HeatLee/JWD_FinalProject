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
    GET_USER_BY_LOGIN("SELECT user_id, login, password, email, user_role_id " +
            "FROM reservation_user " +
            "WHERE email = ?"),
    GET_USER_BY_EMAIL("SELECT user_id, login, password, email, user_role_id " +
            "FROM reservation_user " +
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
    ;

    private String query;

    SQLStatement(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
