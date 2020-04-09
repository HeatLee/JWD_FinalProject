package by.training.finalproject.builder;

import by.training.finalproject.entity.User;
import by.training.finalproject.entity.UserRole;

public class UserBuilder{
    private int userId;
    private String login;
    private String password;
    private String email;
    private UserRole userRole;

    public UserBuilder buildUserId(int id) {
        userId = id;
        return this;
    }

    public UserBuilder buildLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder buildPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder buildEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder buildRole(UserRole userRole) {
        this.userRole = UserRole.getRoleById(userRole.getId());
        return this;
    }

    public User build() {
        return new User(userId, login, password, email, userRole);
    }
}
