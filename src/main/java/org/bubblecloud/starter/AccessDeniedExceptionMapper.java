package org.bubblecloud.starter;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {
    public Response toResponse(AccessDeniedException e) {
        return Response.status(Status.UNAUTHORIZED).type("text/plain").entity(e.getMessage()).build();
    }
}
