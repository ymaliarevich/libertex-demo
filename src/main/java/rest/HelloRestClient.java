package rest;


import javax.ws.rs.core.Response;

public class HelloRestClient extends RestClient {
    private static final String PATH = "hello";

    public Response getHello(String sessionId) {
        return get(PATH, "X-Session-Id", sessionId);
    }

    public Response getHello() {
        return get(PATH);
    }
}
