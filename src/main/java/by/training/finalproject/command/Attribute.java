package by.training.finalproject.command;

public enum Attribute {
    USER_LOGIN("login"),
    USER_ROLE("role"),
    ERR_PARAMETER("err");

    private String attribute;

    Attribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
