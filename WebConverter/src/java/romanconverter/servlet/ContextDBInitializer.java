/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romanconverter.servlet;

import java.time.Clock;
import java.util.HashSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import romanconverter.database.HistoryDAO;

/**
 *Initialize connection with database
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class ContextDBInitializer implements ServletContextListener{
    
    private final String URL_PARAM_NAME = "dbURL";
    
    private final String LOGIN_PARAM_NAME = "dbLogin";
    
    private final String PASSWORD_PARAM_NAME = "dbPassword";
    
    /**
     * Seting up connection with database 
     * url, login, password is pulled from web descriptor
     * @param sce ServletContextEvent containing the ServletContext that is being initialized  
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String dbUrl = (String) context.getInitParameter(URL_PARAM_NAME);
        String dbLogin = (String) context.getInitParameter(LOGIN_PARAM_NAME);
        String dbPassword = (String) context.getInitParameter(PASSWORD_PARAM_NAME);
        if(dbUrl != null && dbLogin != null && dbUrl != null){
            HistoryDAO connection = new HistoryDAO(dbUrl,dbLogin,dbPassword); 
            context.setAttribute("connection", connection);
            System.out.println("Connection established");
        }
        else System.err.println("couldnt connect to DB");
    }

    
    /**
     * Close connection with database
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
