import models.ClientModel;
import models.UsernameModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import rest.LogoutClient;
import steps.Steps;
import utils.Generator;

import javax.ws.rs.core.Response;

public class TestLogoutService extends BaseRestTest {
    private final LogoutClient logoutClient = new LogoutClient();

    @Test
    public void testLogoutService() {
        ClientModel clientModel = Steps.createUniqueClient();

        clientsRestClient.postClients(clientModel);

        UsernameModel usernameModel = new UsernameModel(clientModel.getUsername());
        loginRestClient.postLogin(usernameModel);

        Response response = logoutClient.postLogout(usernameModel);
        Assertions.assertThat(response.getStatus()).as("Verify that client logged out correctly").isEqualTo(200);
    }

    //I assume that logout for non login user should return error
    @Test
    public void testLogoutNonLoginUser() {
        ClientModel clientModel = Steps.createUniqueClient();

        clientsRestClient.postClients(clientModel);

        UsernameModel usernameModel = new UsernameModel(clientModel.getUsername());

        Response response = logoutClient.postLogout(usernameModel);
        Assertions.assertThat(response.getStatus()).isEqualTo(200);
    }

    //I assume that logout for non existing user should return error
    @Test
    public void testLogoutNonExistingUser() {

        UsernameModel usernameModel = new UsernameModel(Generator.getRandomString());

        Response response = logoutClient.postLogout(usernameModel);
        Assertions.assertThat(response.getStatus()).isEqualTo(200);
    }
}
