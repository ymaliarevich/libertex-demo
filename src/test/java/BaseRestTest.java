import rest.ClientsRestClient;
import rest.LoginRestClient;

public class BaseRestTest {
    protected final ClientsRestClient clientsRestClient = new ClientsRestClient();
    protected final LoginRestClient loginRestClient = new LoginRestClient();
}
