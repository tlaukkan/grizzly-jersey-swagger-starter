package org.bubblecloud.starter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ServerTest {

    private Server server = null;
    private Client client;

    @Before
    public void setUp() throws Exception {
        client = Client.create();
        client.addFilter(new ClientRequestResponseFilter());
        server = Server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testGet() {
        ClientResponse response = client.resource(Server.SERVER_URI).path("/test").get(ClientResponse.class);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals("test", response.getEntity(String.class));
    }

    @Test
    public void testPut() {
        ClientResponse response = client.resource(Server.SERVER_URI).path("/test").put(ClientResponse.class, "test");
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals("test!", response.getEntity(String.class));
    }

}
