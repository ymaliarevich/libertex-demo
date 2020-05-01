package steps;

import models.ClientModel;
import models.ClientsResultModel;
import rest.ClientsRestClient;

import java.util.List;

public class Steps {

    public static ClientModel createUniqueClient() {
        ClientModel clientModel;
        List<String> clients =
                new ClientsRestClient().getClients().readEntity(ClientsResultModel.class).getClients();
        do {
            clientModel = new ClientModel();
        } while (clients.contains(clientModel.getUsername()));

        return clientModel;
    }
}
