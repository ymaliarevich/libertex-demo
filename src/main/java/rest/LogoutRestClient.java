package rest;

import models.UsernameModel;

import javax.ws.rs.core.Response;


public class LogoutRestClient extends RestClient {
    private static final String PATH = "logout";

    public Response postLogout(UsernameModel usernameModel) {
        return post(PATH, usernameModel);
    }
}
