package org.bubblecloud.starter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

@Path("/test")
public class TestResource {

    @GET
    public String getTest(@Context HttpHeaders headers) {
        return "test";
    }

}
