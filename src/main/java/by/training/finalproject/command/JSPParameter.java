package by.training.finalproject.command;

public enum JSPParameter {
    COMMAND("command"),
    LOGIN("login"),
    EMAIL("email"),
    PASSWORD("password"),
    PASSWORD_CONFIRMATION("passwordConf"),
    OLD_PASSWORD("oldPassword"),

    private String value;

    JSPParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
