package rest;

import models.UsernameModel;

import javax.ws.rs.core.Response;


public class LogoutClient {
    private static final String PATH = "logout";
    public Response postLogout(UsernameModel usernameModel) {
        return new RestClient().post(PATH, usernameModel);
    }
}
