package rest;


import models.RestModel;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import utils.PropertyProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestClient {
    Client client =
            ClientBuilder.newClient(new ClientConfig().register(
                    new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                            Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000)));

    private static final String TARGET = PropertyProvider.getProperty("target");

    public Response get(String path) {
        return client.target(TARGET).path(path).request().get();
    }

    public Response get(String path, String headerKey, String headerValue) {
        return client.target(TARGET).path(path).request().header(headerKey, headerValue).get();
    }

    public Response post(String path, RestModel model) {
        return client.target(TARGET).path(path).request()
                .post(Entity.json(model));
    }
}
