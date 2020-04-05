package by.training.finalproject.builder;

import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Response;
import by.training.finalproject.entity.Room;

public class ResponseBuilder extends AbstractBuilder<Response> {
    public ResponseBuilder() {
        businessEntity = new Response();
    }

    public void buildId(int id) {
        businessEntity.setId(id);
    }

    public void buildRequest(Request request) {
        businessEntity.setRequest(request);
    }

    public void buildRoom(Room room) {
        businessEntity.setRoom(room);
    }
}
