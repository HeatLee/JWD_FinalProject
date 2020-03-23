package by.training.finalproject.entity;

public enum RoomStatus {
    AVAILABLE(0),
    RESERVED(1),
    PAID(2);

    private int statusId;

    RoomStatus(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }
}
