package romanconverter.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

    /**
     * Model converter 
     */
    private RomanNumberConverter converter = null;
    
    /**
     * History data access object
     */
    private HistoryDAO history = null;
    
    /**
     * object to assist a servlet in sending a request to the client
     */
    private HttpServletRequest request;
    
    /**
     * object to assist a servlet in sending a response to the client
     */
    private HttpServletResponse response;
    
    /**
     *Initialize ConverterServlet
     */
    @Override
    public void init(){
        
        ServletContext context = getServletContext();
        converter = (RomanNumberConverter) context.getAttribute("converter");
        
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
        this.response = response;
        String romanNumber = (String) request.getParameter("romanNumber");
 
        if(romanNumber!=null){
            romanNumber = romanNumber.toUpperCase();
            String presentConversion;
            
            try {
                int result = converter.convert(romanNumber);
                presentConversion = Integer.toString(result);
                updateHistory(romanNumber,result);
                } catch (RomanNumberFormatException ex) {
                    presentConversion = "this is invalid number format";
                    updateHistory(romanNumber,null);            
                }
              
            request.setAttribute("romanNumber", romanNumber.toUpperCase());
            request.setAttribute("result",presentConversion );
            updateCookie(romanNumber,presentConversion);
            } 
        
        checkCookie();
        
        RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
        dis.forward(request, response);
    }   
    
    /**
     * checks cookies for last conversion and send request to view
     */
    private void checkCookie(){
        Cookie[] cookies = request.getCookies();
        String lastConversion;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastConvertion")) {
                    lastConversion = cookie.getValue();
                    request.setAttribute("lastConvertion", lastConversion);
                    break;
                }
            }
    }
    
    /*
     * update cookie with present conversion
     */
    private void updateCookie(String romanNumber, String presentConversion){
        StringBuilder cookieBuilder = new StringBuilder();
        cookieBuilder.append(romanNumber);
        cookieBuilder.append(" = ");
        cookieBuilder.append(presentConversion);
        Cookie cookie = new Cookie("lastConvertion",cookieBuilder.toString() );
        response.addCookie(cookie);
    }
    
    /**
     * update history
     * @param romanNumber String with roman number sended to conversion
     * @param result Integer convertion result, null if roman number was invalid format
     */
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
