package by.training.finalproject.command;

public enum Page {
    INDEX("index.jsp"),
    SIGN_UP("signUp.jsp"),
    SIGN_IN("signIn.jsp"),
    PROFILE("profile.jsp");

    private String value;

     Page(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
