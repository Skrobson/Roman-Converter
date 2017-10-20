/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * @author Skrobol Bartłomiej
 * @version 1.0
 * Signals that a method has been invoked with param in wrong format
 */
public class RomanNumberFormatException extends Exception {

    
    /**Constructs a new exception with the specified detail message.
     * @param message specified detail message*/
    public RomanNumberFormatException(String message){
        super(message);
    }

}
