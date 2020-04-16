package by.training.finalproject.command;

public enum Page {
    INDEX("index.jsp"),
    SIGN_UP("signUp.jsp");

    private String value;

     Page(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
