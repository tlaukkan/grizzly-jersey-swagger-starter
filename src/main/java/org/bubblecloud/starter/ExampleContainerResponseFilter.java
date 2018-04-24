package org.bubblecloud.starter;

import javax.ws.rs.container.*;
import java.io.*;

public class ExampleContainerResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        System.out.println("Container response filter invoked. " + containerRequestContext.getUriInfo().getRequestUri());
        System.out.println(containerResponseContext.getEntity());
    }
}
