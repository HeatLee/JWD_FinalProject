package by.training.finalproject.entity;

public enum UserRole {
    ADMIN(2),
    USER(3),
    GUEST(4),
    UNSUPPORTED_INDEX(0);

    private int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserRole getRoleById(int id) {
        for (UserRole role :
                UserRole.values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        return UNSUPPORTED_INDEX;
    }
}
