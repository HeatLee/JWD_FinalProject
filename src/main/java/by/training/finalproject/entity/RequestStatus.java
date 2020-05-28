package by.training.finalproject.entity;

public enum RequestStatus {
    APPROVED(2, "approved"),
    PAID(1, "paid"),
    PROCESS(3, "process"),
    UNSUPPORTED(0, "unsupported");

    private int id;
    private String text;

    RequestStatus(int id, String text) {
        this.id = id;
        this.text = text;
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

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
