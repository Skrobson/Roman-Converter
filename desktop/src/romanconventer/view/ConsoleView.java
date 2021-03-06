
package romanconventer.view;


import java.io.Closeable;
import java.util.Scanner;

/**
 * Responsible for comunication whith user
 * @author Skrobol Bartłomiej
 * @version 2.0
 */
public class ConsoleView implements Closeable {
    
    /**For getting input from user */
    private  Scanner scanner;
    
    public ConsoleView(){
        
        scanner = new Scanner(System.in);
    }
    
    /**
     * Prints a String on console and then terminate the line. 
     * @param message String to print
     */
    public void printMessage(String message){
            
        System.out.println(message);     
    }
    /**
     * Prints convertion result on console and the terminate the line
     * @param romanNumber roman number
     * @param result arabic number to print 
     */
    public void printResult(String romanNumber , int result){
        System.out.println(romanNumber.toUpperCase() + " in arabic is " + result);
    }
    /**
     *@return string from console
     */
    public String getInput(){
        
        String input = scanner.nextLine();
        return input;
    }
    
    /**
     * Prints a error String on console and then terminate the line.
     * @param error message to print
     */    
    public void showErrorMessage(String error){
        
        System.err.println(error); 
    }
    
    
    /**
     * Implementetion Closeable interface, closing input stream
     */
    @Override
    public void close(){
        scanner.close();
    }
    
  
}
