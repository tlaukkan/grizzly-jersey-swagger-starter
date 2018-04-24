package org.bubblecloud.starter;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.core.util.ReaderWriter;
import org.glassfish.jersey.server.ContainerException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClientRequestResponseFilter extends ClientFilter {

   @Override
   public ClientResponse handle(ClientRequest request) throws ClientHandlerException {
       System.out.println("Client request filter invoked. " + request.getURI());
       if (request.getEntity() != null) {
           System.out.println(request.getEntity());
       }
       ClientResponse response = getNext().handle(request);
       System.out.println("Client response filter invoked. " + request.getURI());
       final ByteArrayOutputStream out = new ByteArrayOutputStream();
       final InputStream in = response.getEntityInputStream();
       try {
           if (in.available() > 0) {
               ReaderWriter.writeTo(in, out);
               byte[] requestEntity = out.toByteArray();
               System.out.println(new String(requestEntity));
               response.setEntityInputStream(new ByteArrayInputStream(requestEntity));
           }
       } catch (IOException ex) {
           throw new ContainerException(ex);
       }

       return response;
   }
}