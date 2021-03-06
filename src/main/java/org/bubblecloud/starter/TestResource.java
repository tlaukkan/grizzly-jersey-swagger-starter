package org.bubblecloud.starter;

import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

@Api
@Path("/test")
public class TestResource {

    @GET
    public String getTest(@Context HttpHeaders headers) {
        return "test";
    }

    @PUT
    public String putTest(@Context HttpHeaders header, final String message) {
        return message + "!";
    }

}
