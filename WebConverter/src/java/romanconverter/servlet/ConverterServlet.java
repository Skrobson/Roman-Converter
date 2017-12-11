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
    
    private HistoryDAO history = null;
    
    private HttpServletRequest request;
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
        
        Connection con =(Connection) context.getAttribute("connection");
        if(con != null){
            history = new HistoryDAO(con);
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
        this.request=request;
        String romanNumber = (String) request.getParameter("romanNumber");
        
        if(romanNumber!=null){
            try {
                int result = converter.convert(romanNumber);
                request.setAttribute("result", Integer.toString(result));
                updateHistory(romanNumber,result);
                } catch (RomanNumberFormatException ex) {
                    request.setAttribute("result" , ex.getMessage());
                    updateHistory(romanNumber,null);            
                }
            }            
        
        RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
        dis.forward(request, response);
    }   
    

    private void updateHistory(String romanNumber, Integer result){
        if(history != null){
            try{
                String ip = request.getRemoteAddr();
                if(result == null){
                    history.updateIncorrectConverions(ip, romanNumber);
                }else {
                    history.updateCorrectConverions(ip, romanNumber, result);
                }
            }catch (SQLException sqlex){
                    request.setAttribute("sqlError" , sqlex.getMessage());
            }
        }
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
