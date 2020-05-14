package by.training.finalproject.entity;

public enum RequestStatus {
    APPROVED(2),
    PAID(1),
    PROCESS(3),
    UNSUPPORTED(0);

    private int id;

    RequestStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static RequestStatus getStatusById(int id) {
        for (RequestStatus status :
                RequestStatus.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        return RequestStatus.UNSUPPORTED;
    }

    public static boolean isContainId(int id) {
        for (RequestStatus status : RequestStatus.values()) {
            if (status.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
