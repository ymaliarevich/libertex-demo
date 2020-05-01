package rest;

import models.ClientModel;

import javax.ws.rs.core.Response;


public class ClientsRestClient {
    private static final String PATH = "clients";

    public Response getClients() {
        return new RestClient().get(PATH);
    }

    public Response postClients(ClientModel clientModel) {
        return new RestClient().post(PATH, clientModel);
    }
}
