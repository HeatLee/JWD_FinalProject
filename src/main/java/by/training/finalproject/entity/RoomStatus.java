package by.training.finalproject.entity;

public enum RoomStatus {
    AVAILABLE(1),
    RESERVED(2),
    PAID(3),
    UNSUPPORTED_INDEX(0);;

    private int statusId;

    RoomStatus(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }
}
