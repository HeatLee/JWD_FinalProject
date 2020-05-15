package by.training.finalproject.dao;

public enum SQLTableLabel {
    USER_LOGIN("login"),
    USER_PASSWORD("password"),
    USER_EMAIL("email"),
    USER_ROLE_ID("user_role_id"),
    USER_ID("user_id"),
    ROOM_ID("hotel_room_id"),
    ROOM_CAPACITY("room_capacity"),
    ROOM_PRICE("room_price"),
    ROOM_STATUS_ID("room_status_id"),
    HOTEL_ID("hotel_id"),
    HOTEL_NAME("hotel_name"),
    HOTEL_STARS("stars"),
    ADDRESS_ID("address_id"),
    ADDRESS_COUNTRY("country"),
    ADDRESS_TOWN("town"),
    REQUEST_ID("request_id"),
    REQUEST_CAPACITY("capacity"),
    REQUEST_CHECK_IN("check_in"),
    REQUEST_DEPARTURE("departure"),
    REQUEST_STARS("stars"),
    REQUEST_STATUS_ID("status_id"),
    RESPONSE_ID("response_id");

    SQLTableLabel(String label) {
        this.label = label;
    }

    private String label;

    public String getLabel() {
        return label;
    }
}
