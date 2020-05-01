import rest.ClientsRestClient;
import rest.LoginRestClient;

public class BaseRestTest {
    protected ClientsRestClient clientsRestClient = new ClientsRestClient();
    protected LoginRestClient loginRestClient = new LoginRestClient();
}
