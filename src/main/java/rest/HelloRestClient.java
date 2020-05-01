package rest;


import javax.ws.rs.core.Response;

public class HelloRestClient {
    private static final String PATH = "hello";

    public Response getHello(String sessionId) {
        return new RestClient().get(PATH, "X-Session-Id", sessionId);
    }

    public Response getHello() {
        return new RestClient().get(PATH);
    }
}
