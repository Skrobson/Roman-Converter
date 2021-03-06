package romanconverter.model;

/**
 * Signals that a method has been invoked with param in wrong format
 * @author Skrobol BartĹ‚omiej
 * @version 1.0
 */
public class RomanNumberFormatException extends Exception {

    
    /**Constructs a new exception with the specified detail message.
     * @param message specified detail message*/
    public RomanNumberFormatException(String message){
        super(message);
    }

}