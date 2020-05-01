package rest;

import models.UsernameModel;

import javax.ws.rs.core.Response;

public class LoginRestClient {
    private static final String PATH = "login";

    public Response postLogin(UsernameModel usernameModel) {
        return new RestClient().post(PATH, usernameModel);
    }
}
