/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romanconverter.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import romanconverter.model.RomanNumberConverter;


/**
 * Initialize connection with database
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class ContextInitializer implements ServletContextListener{
    
    /**
     * InitParameter url string
     */
    private final String URL_PARAM_NAME = "dbURL";
    
    /**
     * InitParameter login string
     */
    private final String LOGIN_PARAM_NAME = "dbLogin";
    
    /**
     * InitParameter password string
     */
    private final String PASSWORD_PARAM_NAME = "dbPassword";
    
    /**
     * A connection (session) with a specific database
     */
    private Connection connection = null;
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
        
        if(dbUrl != null && dbLogin != null && dbPassword != null){ 
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");          
                connection = DriverManager.getConnection(dbUrl, dbLogin, dbPassword);
                
            } catch (SQLException | ClassNotFoundException ex) {
              context.setAttribute("connectionError", ex.getMessage());
            } 
            context.setAttribute("connection", connection);
        }
        
        
            RomanNumberConverter converter = new RomanNumberConverter();
            context.setAttribute("converter",converter);
    }

    
    /**
     * Close connection with database
     * @param sce this param 
     * ServletContextEvent containing the ServletContext that is being destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException ex) {    
            }
        }
    }
    
}
