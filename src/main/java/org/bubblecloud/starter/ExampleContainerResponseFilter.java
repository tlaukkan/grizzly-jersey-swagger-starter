package org.bubblecloud.starter;

import javax.ws.rs.container.*;
import java.io.*;

public class ExampleContainerResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(final ContainerRequestContext containerRequestContext, final ContainerResponseContext containerResponseContext) throws IOException {
        System.out.println("Container response filter invoked. " + containerRequestContext.getUriInfo().getRequestUri());
        if (containerResponseContext.getEntity() != null) {
            System.out.println(containerResponseContext.getEntity());
        }
    }
}
