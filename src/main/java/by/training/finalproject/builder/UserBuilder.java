package by.training.finalproject.builder;

import by.training.finalproject.entity.User;
import by.training.finalproject.entity.UserRole;

public class UserBuilder extends AbstractBuilder<User>{

    public UserBuilder() {
        this.businessEntity = new User();
    }

    public void buildUserId(int id) {
        businessEntity.setUserId(id);
    }

    public void buildLogin(String login) {
        businessEntity.setLogin(login);
    }

    public void buildPassword(String password) {
        businessEntity.setPassword(password);
    }

    public void buildEmail(String email) {
        businessEntity.setEmail(email);
    }

    public void buildRole(UserRole userRole) {
        businessEntity.setUserRole(userRole);
    }
}
