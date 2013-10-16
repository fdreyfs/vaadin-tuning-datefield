package org.vaadin.addons.tuningdatefield.demo;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.vaadin.server.VaadinServlet;

// web.xml because of google appengine 
//@WebListener
public class DemoWebAppInitializer implements ServletContextListener {
    
    public static final String PRODUCTION_MODE = Boolean.toString(true);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ServletRegistration.Dynamic vaadinServletRegistration = context.addServlet("DemoServlet",
                MyServlet.class);
        vaadinServletRegistration.addMapping("/*");
        vaadinServletRegistration.setAsyncSupported(true);
        vaadinServletRegistration.setLoadOnStartup(10);
        vaadinServletRegistration.setInitParameter("productionMode", PRODUCTION_MODE);
        vaadinServletRegistration.setInitParameter("UIProvider", TuningDateFieldUIProvider.class.getName());
        vaadinServletRegistration.setInitParameter("widgetset",
                "org.vaadin.addons.tuningdatefield.demo.widgetset.TuningDateFieldDemoWidgetset");

    }
    
    // extends GAEVaadinServlet for GAE deployment
    public static class MyServlet extends VaadinServlet {
        
        private static final long serialVersionUID = 2122520422024963397L;
        
        private static final Logger logger = Logger.getLogger(MyServlet.class.getName());

        @Override
        public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
            logger.info("Received request : "+req);
            super.service(req, res);
        }
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}