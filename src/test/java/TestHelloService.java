import models.ClientModel;
import models.HelloModel;
import models.UsernameModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import rest.HelloRestClient;
import rest.LogoutRestClient;
import steps.Steps;
import utils.Constants;
import utils.Generator;

import javax.ws.rs.core.Response;


public class TestHelloService extends BaseRestTest {
    private final HelloRestClient helloRestClient = new HelloRestClient();
    private final LogoutRestClient logoutRestClient = new LogoutRestClient();

    @Test
    public void testHelloClient() {
        ClientModel clientModel = Steps.createUniqueClient();

        Response response = clientsRestClient.postClients(clientModel);
        Assertions.assertThat(response.getStatus()).isEqualTo(200);

        UsernameModel usernameModel = new UsernameModel(clientModel.getUsername());
        response = loginRestClient.postLogin(usernameModel);
        Assertions.assertThat(response.getStatus()).isEqualTo(200);

        String sessionId = response.getHeaderString(Constants.SESSION_ID);

        response = helloRestClient.getHello(sessionId);

        HelloModel helloModel = response.readEntity(HelloModel.class);
        HelloModel expectedHelloModel = new HelloModel(Constants.OK, String.format(Constants.MESSAGE_TMPL, clientModel.getFullName()));

        Assertions.assertThat(response.getStatus()).as("Verify that hello user service works correctly").isEqualTo(200);
        Assertions.assertThat(helloModel).isEqualTo(expectedHelloModel);

        response = logoutRestClient.postLogout(usernameModel);
        Assertions.assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testHelloClientForLoggedOutClient() {
        ClientModel clientModel = Steps.createUniqueClient();

        Response response = clientsRestClient.postClients(clientModel);
        Assertions.assertThat(response.getStatus()).isEqualTo(200);

        UsernameModel usernameModel = new UsernameModel(clientModel.getUsername());
        response = loginRestClient.postLogin(usernameModel);
        Assertions.assertThat(response.getStatus()).isEqualTo(200);

        String sessionId = response.getHeaderString(Constants.SESSION_ID);

        response = logoutRestClient.postLogout(usernameModel);
        Assertions.assertThat(response.getStatus()).isEqualTo(200);

        response = helloRestClient.getHello(sessionId);

        Assertions.assertThat(response.getStatus()).as("Verify that hello service returns 401 for existing but logged out client").isEqualTo(401);
    }

    @Test
    public void testHelloClientWithoutSessionHeader() {
        Response response = helloRestClient.getHello();

        Assertions.assertThat(response.getStatus()).as("Verify that hello service without header returns 401").isEqualTo(401);
    }

    @Test
    public void testHelloClientWithNonExistingSession() {
        Response response = helloRestClient.getHello(Generator.getRandomString());

        Assertions.assertThat(response.getStatus()).as("Verify that hello service with non existing user returns 401").isEqualTo(401);
    }
}
