package by.training.finalproject.entity;

public enum UserRole {
    ADMIN(0),
    USER(1),
    GUEST(2);

    private int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
