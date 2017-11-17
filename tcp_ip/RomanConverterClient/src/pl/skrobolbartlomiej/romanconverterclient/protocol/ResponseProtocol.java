package pl.skrobolbartlomiej.romanconverterclient.protocol;

/**
 * Set of response code's Strings 
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class ResponseProtocol {
    
    /**Hand shake code*/
    static final public String HELLO = "100";
    
    /**Request confirmation without any payload*/
    static final public String  OK = "101";
    
    /**Response with converted roman number in decimal format */
    static final public String DECIMAL_RESULT = "102";
    
    /**Signal when we try to convert Roman number without previusly sended argument */
    static final public String MISSING_ARGUMENT = "403";
    
    /**Unknow request*/
    static final public String UNKNOWN_COMMAND = "400";
    
    /**Wrong format of sended roman number */
    static final public String WRONG_ROMAN_FORMAT = "401";
    
    
    
    /**Started every line of help information */
    static final public String HELP_ = "200";
    
    /**Signal that lines of help informations ended, without any payload */
    static final public String HELP_END = "201";
}
