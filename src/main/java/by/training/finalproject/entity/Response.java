package by.training.finalproject.entity;

import java.util.Objects;

public class Response {
    private int id;
    private Request request;
    private Room room;

    public Response(int id, Request request, Room room) {
        this.id = id;
        this.request = request;
        this.room = room;
    }

    public Response(Response response) {
        this.id = response.id;
        this.request = new Request(response.request);
        this.room = new Room(response.room);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        if (id != response.id) return false;
        if (!Objects.equals(request, response.request)) return false;
        return Objects.equals(room, response.room);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (request != null ? request.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", request=" + request +
                ", room=" + room +
                '}';
    }
}
