package romanconverter.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import romanconverter.database.HistoryDAO;

/**
 * Geting convertion history from database end forward to history.jsp
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class ConvertionHistory extends HttpServlet {
    
     /**
     * History data access object
     */
    HistoryDAO history = null;
    
     /**
     *Initialize ConverterServlet
     */
    @Override
    public void init(){
        
        ServletContext context = getServletContext();
        
        Connection con =(Connection) context.getAttribute("connection");
        if(con != null){
            history = new HistoryDAO(con);
        }
    
        
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(history != null){
            try{
                request.setAttribute("correctConversions", history.getCorrectConversions());
                request.setAttribute("incorrectConversions", history.getIncorreConversions());
            }catch (SQLException ex){
                request.setAttribute("error", ex.getMessage());
            }
        }
        
        RequestDispatcher dis = request.getRequestDispatcher("history.jsp");
        dis.forward(request, response);

    }

   
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
