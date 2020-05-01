import models.ClientModel;
import models.HelloModel;
import models.UsernameModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import rest.HelloRestClient;
import rest.LogoutClient;
import steps.Steps;
import utils.Constants;
import utils.Generator;

import javax.ws.rs.core.Response;


public class TestHelloService extends BaseRestTest {
    private HelloRestClient helloRestClient = new HelloRestClient();
    private LogoutClient logoutClient = new LogoutClient();

    @Test
    public void testHelloClient() {
        ClientModel clientModel = Steps.createUniqueClient();

        Response response = clientsRestClient.postClients(clientModel);
        Assertions.assertThat(response.getStatus()).as("Verify that client creates successfully").isEqualTo(200);

        UsernameModel usernameModel = new UsernameModel(clientModel.getUsername());
        response = loginRestClient.postLogin(usernameModel);
        Assertions.assertThat(response.getStatus()).as("Verify that client login successfully").isEqualTo(200);

        String sessionId = response.getHeaderString(Constants.SESSION_ID);

        response = helloRestClient.getHello(sessionId);

        HelloModel helloModel = response.readEntity(HelloModel.class);
        HelloModel expectedHelloModel = new HelloModel(Constants.OK, String.format(Constants.MESSAGE_TMPL, clientModel.getFullName()));

        Assertions.assertThat(response.getStatus()).as("Verify that hello user works successfully").isEqualTo(200);
        Assertions.assertThat(helloModel).isEqualTo(expectedHelloModel);

        response = logoutClient.postLogout(usernameModel);
        Assertions.assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testHelloClientWithoutSessionHeader() {
        Response response = helloRestClient.getHello();

        Assertions.assertThat(response.getStatus()).isEqualTo(401);
    }

    @Test
    public void testHelloClientWithNonExistingSession() {
        Response response = helloRestClient.getHello(Generator.getRandomString());

        Assertions.assertThat(response.getStatus()).isEqualTo(401);
    }
}
