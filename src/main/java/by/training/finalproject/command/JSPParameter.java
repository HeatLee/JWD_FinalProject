package by.training.finalproject.command;

public enum JSPParameter {
    COMMAND("command"),
    LOGIN("login"),
    EMAIL("email"),
    PASSWORD("password"),
    PASSWORD_CONFIRMATION("passwordConf"),
    OLD_PASSWORD("oldPassword"),
    TOWN("town"),
    COUNTRY("country"),
    CAPACITY("capacity"),
    STARS("stars"),
    CHECK_IN("checkIn"),
    DEPARTURE("departure");

    private String value;

    JSPParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
