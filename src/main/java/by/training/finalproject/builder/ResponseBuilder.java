package by.training.finalproject.builder;

import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Response;
import by.training.finalproject.entity.Room;

public class ResponseBuilder {

    private int id;
    private Request request;
    private Room room;

    public ResponseBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    public ResponseBuilder buildRequest(Request request) {
        this.request = new Request(request);
        return this;
    }

    public ResponseBuilder buildRoom(Room room) {
        this.room = new Room(room);
        return this;
    }

    public Response build() {
        return new Response(id, request, room);
    }
}
