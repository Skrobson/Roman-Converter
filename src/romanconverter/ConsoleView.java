/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romanconverter;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class ConsoleView implements Closeable{
    
    private final Scanner scanner;
    
    public ConsoleView(){
        
        scanner = new Scanner(System.in);
    }
    
    /**
     * Prints a String on console and then terminate the line. 
     */
    public void printMessage(String message){
            
        System.out.println(message);     
    }
    
    /**
     *@return string from console
     */
    public String getInput()throws IllegalStateException{
        
        String input = scanner.nextLine();
        return input;
    }
    
    /**
     * Prints a error String on console and then terminate the line.
     */    
    public void showErrorMessage(String error){
        
        System.err.println(error); 
    }
    
    
    @Override
    public void close() throws IOException {
        
        scanner.close();
    }
}
