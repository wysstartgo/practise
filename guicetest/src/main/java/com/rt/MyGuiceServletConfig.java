package com.rt;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class MyGuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                System.out.println("MyGSC->getInjector->configureServlets"); //I'm seeing this in the console...
                serve("/guice").with(MyServlet.class);
            }
        });
    }
}