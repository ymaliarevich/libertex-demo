package rest;

import models.UsernameModel;

import javax.ws.rs.core.Response;

public class LoginRestClient extends RestClient {
    private static final String PATH = "login";

    public Response postLogin(UsernameModel usernameModel) {
        return post(PATH, usernameModel);
    }
}
