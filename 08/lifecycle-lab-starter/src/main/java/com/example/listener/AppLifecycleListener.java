package com.example.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

import javax.naming.*;

/**
 * Application lifecycle listener — fires at Tomcat startup and shutdown.
 *
 * LAB 1 — Task 1.1 (baseline): contextInitialized() already logs the app version.
 * LAB 2 — Task 2.2: bind a greeting string to JNDI inside contextInitialized().
 *
 * This @WebListener is registered automatically by Tomcat via the
 * @WebListener annotation — no web.xml entry is needed.
 *
 * Startup order:
 *   1. Tomcat creates DataSource (from context.xml)
 *   2. Tomcat fires contextInitialized() on all @WebListener instances
 *   3. JAX-RS application starts, resource classes become available
 */
@WebListener
public class AppLifecycleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        // Read the app version from init-params (already works without any changes)
        String version = ctx.getInitParameter("app.version");
        ctx.log("[AppLifecycleListener] Application starting — version: " + version);

        // ── LAB 2 Task 2.2 — bind custom objects to JNDI ─────────────────
        //
        // TODO: bind a greeting string to java:comp/env/app/greeting
        //
        // Pattern:
        //   try {
        //       InitialContext ic = new InitialContext();
        //
        //       // Ensure subcontext exists
        //       try { ic.lookup("java:comp/env/app"); }
        //       catch (NameNotFoundException e) {
        //           ic.createSubcontext("java:comp/env/app"); }
        //
        //       // Bind the greeting (rebind overwrites if already exists)
        //       ic.rebind("java:comp/env/app/greeting",
        //           "Welcome to " + version);
        //
        //       ctx.log("[AppLifecycleListener] JNDI resources bound.");
        //   } catch (NamingException e) {
        //       throw new RuntimeException("JNDI binding failed", e);
        //   }

        ctx.log("[AppLifecycleListener] Initialisation complete.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("[AppLifecycleListener] Application shutting down.");
    }
}
