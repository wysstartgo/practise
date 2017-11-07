package com.rt;

import com.google.inject.Guice;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;

import static java.util.EnumSet.allOf;

public class Main {
    public static void main(String... args) throws Exception {
        Guice.createInjector(new MyServletModule());
        Server server = new Server(8080);
        ServletContextHandler handler =
                new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        handler.addFilter(GuiceFilter.class, "/*", allOf(DispatcherType.class));
        handler.addServlet(DefaultServlet.class, "/");
        server.start();
    }
}