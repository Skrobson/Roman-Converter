package pl.skrobolbartlomiej.romanconverterclient.protocol;

/**
 * Set of request commands strings
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class RequestProtocol {
    /**Set roman number for convert */
    static final public String SETR = "SETR";
    
    /**Get converted number */
    static final public String GETD ="GETD";
    
    /**Get list of prompts */
    static final public String HELP = "HELP";
    
    /**Exit, disconect from server */
    static final public String EXIT = "EXIT";
    
}
