import models.ClientModel;
import models.ClientsResultModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import steps.Steps;
import utils.Generator;

import javax.ws.rs.core.Response;
import java.util.List;

public class TestClientsService extends BaseRestTest {

    @Test
    public void testClientsService() {
        ClientModel clientModel = Steps.createUniqueClient();
        Response response = clientsRestClient.postClients(clientModel);
        Assertions.assertThat(response.getStatus()).as("Verify that client created correctly").isEqualTo(200);

        List<String> existingClient = clientsRestClient.getClients().readEntity(ClientsResultModel.class).getClients();
        Assertions.assertThat(existingClient).as("Verify that client exists").contains(clientModel.getUsername());
    }

    @Test
    public void testClientsServiceExistingFullName() {
        ClientModel clientModel = Steps.createUniqueClient();
        Response response = clientsRestClient.postClients(clientModel);
        Assertions.assertThat(response.getStatus()).isEqualTo(200);

        clientModel.setUsername(Generator.getRandomString());
        response = clientsRestClient.postClients(clientModel);
        Assertions.assertThat(response.getStatus()).as("Verify that client with existing fullname created correctly").isEqualTo(200);

        List<String> existingClient = clientsRestClient.getClients().readEntity(ClientsResultModel.class).getClients();
        Assertions.assertThat(existingClient).as("Verify that client with existing fullname exists").contains(clientModel.getUsername());
    }

    @Test
    public void testClientsServiceExistingUsername() {
        ClientModel clientModel = Steps.createUniqueClient();
        Response response = clientsRestClient.postClients(clientModel);
        Assertions.assertThat(response.getStatus()).isEqualTo(200);

        clientModel.setFullName(Generator.getRandomString());
        response = clientsRestClient.postClients(clientModel);
        Assertions.assertThat(response.getStatus()).as("Verify that client with existing username is not created").isEqualTo(500);
    }

    @Test
    public void testClientsServiceNullFullName() {
        ClientModel clientModel = new ClientModel(null, Generator.getRandomString());
        Response response = clientsRestClient.postClients(clientModel);
        Assertions.assertThat(response.getStatus()).as("Verify that user without fullname is not created").isEqualTo(500);

        List<String> existingClient = clientsRestClient.getClients().readEntity(ClientsResultModel.class).getClients();
        Assertions.assertThat(existingClient).as("Verify that client is not created").doesNotContain(clientModel.getUsername());
    }

    @Test
    public void testClientsServiceNullUserName() {
        ClientModel clientModel = new ClientModel(Generator.getRandomString(), null);
        Response response = clientsRestClient.postClients(clientModel);
        Assertions.assertThat(response.getStatus()).as("Verify that user without fullname is not created").isEqualTo(500);

        List<String> existingClient = clientsRestClient.getClients().readEntity(ClientsResultModel.class).getClients();
        Assertions.assertThat(existingClient).as("Verify that client without fullname is not created").doesNotContain(clientModel.getUsername());
    }
}
