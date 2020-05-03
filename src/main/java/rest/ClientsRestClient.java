package rest;

import models.ClientModel;

import javax.ws.rs.core.Response;


public class ClientsRestClient extends RestClient {
    private static final String PATH = "clients";

    public Response getClients() {
        return get(PATH);
    }

    public Response postClients(ClientModel clientModel) {
        return post(PATH, clientModel);
    }
}
