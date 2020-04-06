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
    ;

    private String query;

    SQLStatement(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
