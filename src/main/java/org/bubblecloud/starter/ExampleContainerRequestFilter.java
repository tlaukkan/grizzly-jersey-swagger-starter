package org.bubblecloud.starter;

import com.sun.jersey.core.util.ReaderWriter;
import org.glassfish.jersey.server.ContainerException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class ExampleContainerRequestFilter implements ContainerRequestFilter {
    @Override
    public void filter(final ContainerRequestContext filterContext) throws IOException {
        System.out.println("Container request filter invoked. " + filterContext.getUriInfo().getRequestUri());
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final InputStream in = filterContext.getEntityStream();
        try {
            if (in.available() > 0) {
                ReaderWriter.writeTo(in, out);
                byte[] requestEntity = out.toByteArray();
                System.out.println(new String(requestEntity));
                filterContext.setEntityStream(new ByteArrayInputStream(requestEntity));
            }
        } catch (IOException ex) {
            throw new ContainerException(ex);
        }
    }
}
