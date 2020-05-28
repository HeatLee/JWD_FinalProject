package by.training.finalproject.command;

public enum Attribute {
    USER("user"),
    ERR_PARAMETER("err"),
    REQUEST_LIST("requestList"),
    REQUEST("request"),
    ROOM_LIST("rooms"),
    HOTEL_LIST("hotelList"),
    RESPONSE("userResponse"),
    LANGUAGE("language");

    private String attribute;

    Attribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
