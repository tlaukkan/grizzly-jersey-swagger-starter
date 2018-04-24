package org.bubblecloud.starter;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class ServerTest {

    private Server server = null;

    @Before
    public void setUp() throws Exception {
        server = Server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testGet() {
        final ClientConfig clientConfig = new ClientConfig().connectorProvider(new GrizzlyConnectorProvider());
        Client client = ClientBuilder.newBuilder().withConfig(clientConfig).build();
        final Response response = client.target(Server.SERVER_URI).path("/test").request().get(Response.class);
        assertEquals(200, response.getStatus());
    }

}
