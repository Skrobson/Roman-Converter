package pl.skrobolbartlomiej.romanconverterclient.view;

import java.util.Scanner;

/**
 * Responsible for comunication whith user
 * @author Skrobol Bartłomiej
 * @version 2.0
 */
public class ConsoleView {
    
    /**For getting input from user */
    private  Scanner scanner;
    
    /**Construct new console view*/
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
    public void printResult(String romanNumber , String result){
        System.out.println(romanNumber.toUpperCase() + " in arabic is " + result);
    }
    /**Retrive roman number from console
     *@return string from console
     */
    public String retrieveNumber(){
        
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
}