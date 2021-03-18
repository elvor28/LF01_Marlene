package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class RestServer {
    public static void main(String[] args) throws Exception {


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                ControllerInterface.class.getCanonicalName());

        ServletHolder holderHome = new ServletHolder("static-home", DefaultServlet.class);
        holderHome.setInitParameter("resourceBase", "src/main/resources/static");
        holderHome.setInitParameter("dirAllowed", "true");
        holderHome.setInitParameter("pathInfoOnly", "true");
        context.addServlet(holderHome, "/static/*");

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }

    }
}