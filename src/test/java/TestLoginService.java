import models.ClientModel;
import models.UsernameModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import steps.Steps;
import utils.Constants;
import utils.Generator;

import javax.ws.rs.core.Response;

public class TestLoginService extends BaseRestTest {

    @Test
    public void testLoginService() {
        ClientModel clientModel = Steps.createUniqueClient();

        clientsRestClient.postClients(clientModel);

        UsernameModel usernameModel = new UsernameModel(clientModel.getUsername());
        Response response = loginRestClient.postLogin(usernameModel);
        Assertions.assertThat(response.getStatus()).as("Verify that client login successfully").isEqualTo(200);
        Assertions.assertThat(response.getHeaderString(Constants.SESSION_ID)).isNotNull();
    }

    @Test
    public void testLoginServiceNonExitingClient() {
        UsernameModel usernameModel = new UsernameModel(Generator.getRandomString());
        Response response = loginRestClient.postLogin(usernameModel);

        Assertions.assertThat(response.getStatus()).as("Verify that client login successfully").isEqualTo(500);
        Assertions.assertThat(response.getHeaderString(Constants.SESSION_ID)).isNull();
    }
}
