package org.bubblecloud.starter;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.grizzly.http.server.HttpServer;

public class Server {

    public static final URI SERVER_URI = UriBuilder.fromUri("http://localhost/").port(8463).build();

    private final HttpServer httpServer;

    private Server(final HttpServer httpServer) {
        this.httpServer = httpServer;
    }

    public static Server start() throws IOException {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost(SERVER_URI.getHost() + ":" + SERVER_URI.getPort());
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage("org.bubblecloud.starter");
        beanConfig.setScan(true);

        ResourceConfig rc = new ResourceConfig();
        rc.registerClasses(
                TestResource.class,
                ExampleContainerRequestFilter.class,
                ExampleContainerResponseFilter.class,
                AccessDeniedExceptionMapper.class,
                ApiListingResource.class,
                SwaggerSerializers.class);

        final HttpServer grizzlyServer = GrizzlyHttpServerFactory.createHttpServer(SERVER_URI, rc, true);

        System.out.println(SERVER_URI.toString());
        grizzlyServer.start();

        return new Server(grizzlyServer);
    }

    public void stop() {
        httpServer.shutdownNow();
    }

    public static void main(final String[] args) throws InterruptedException, IOException {
        start();
    }
}
