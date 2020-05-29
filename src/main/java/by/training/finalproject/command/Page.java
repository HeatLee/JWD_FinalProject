package by.training.finalproject.command;

public enum Page {
    INDEX("index.jsp", "INDEX_PAGE"),
    ERROR_500_JSP("page/error500.jsp", "500"),
    SIGN_UP("signUp.jsp", "SIGN_UP_PAGE"),
    SIGN_IN("signIn.jsp", "SIGN_IN_PAGE"),
    PROFILE("user/profile.jsp", "PROFILE_PAGE"),
    ADMIN_PAGE("admin/adminPage.jsp", "ADMIN_PAGE"),
    CREATE_RESPONSE_PAGE("admin/createResponse.jsp", "CREATE_RESPONSE_PAGE"),
    ROOM_INFO("user/roomInfo.jsp", "GET_USER_RESPONSE");

    private String value;
    private String commandName;

     Page(String value, String commandName) {
        this.value = value;
         this.commandName = commandName;
    }

    public String getValue() {
        return value;
    }

    public String getCommandName() {
        return commandName;
    }

    public static String getCommandNameByPath(String path) {
        for (Page page : Page.values()) {
            if (page.getValue().equals(path)) {
                return page.getCommandName();
            }
        }
        return ERROR_500_JSP.getCommandName();
    }
}
