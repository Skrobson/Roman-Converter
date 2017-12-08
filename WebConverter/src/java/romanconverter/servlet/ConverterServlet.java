package romanconverter.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import romanconverter.database.DatabaseConnection;
import romanconverter.model.RomanNumberConverter;
import romanconverter.model.RomanNumberFormatException;

/**
 * Responsible for converting roman number 
 * @author Skrobol Bartlomiej
 * @version 1.0
 */
public class ConverterServlet extends HttpServlet {

    private final String CONVERTER = "converter";
    
    private RomanNumberConverter converter = null;
    
    private DatabaseConnection connection = null;
    
    /**
     *Initialize ConverterServlet
     */
    @Override
    public void init(){
        
        ServletContext context = getServletContext();
        converter = (RomanNumberConverter) context.getAttribute(CONVERTER);
        if (converter== null){
            converter = new RomanNumberConverter();
            context.setAttribute(CONVERTER,converter);
        }
        
        connection = (DatabaseConnection) context.getAttribute("Connection");
        if (connection == null){
            connection = new DatabaseConnection();
            context.setAttribute("Connection",connection);
            
        }
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Converting roman number given in request parameter "romanNumber"
     * and forwarding result to index.jsp in request attribute "result"
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String romanNumber = (String) request.getParameter("romanNumber");
        
        if(romanNumber!=null){
            try {
                int result = converter.convert(romanNumber);
                request.setAttribute("result", Integer.toString(result));
                connection.updateHistory(request.getRemoteAddr(), romanNumber, result);
            } catch (RomanNumberFormatException ex) {
                request.setAttribute("result" , ex.getMessage());
            }
        }    

        RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
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
