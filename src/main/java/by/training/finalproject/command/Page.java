package by.training.finalproject.command;

public enum Page {
    INDEX("index.jsp"),
    SIGN_UP("signUp.jsp"),
    SIGN_IN("signIn.jsp"),
    PROFILE("user/profile.jsp"),
    ADMIN_PAGE("admin/adminPage.jsp"),
    CREATE_RESPONSE_PAGE("admin/createResponse.jsp"),
    ROOM_INFO("user/roomInfo.jsp");

    private String value;

     Page(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
