package pl.skrobolbartlomiej.romanconverter.protocol;
import static pl.skrobolbartlomiej.romanconverter.protocol.RequestProtocol.*;
import static pl.skrobolbartlomiej.romanconverter.protocol.ResponseProtocol.*;
import java.util.ArrayList;

/**
 *
 * @author Skrobol Bartłomiej
 */
public class HelpInstruction {
    
    /**Set of server commands*/
    private ArrayList<String> instructions;
    {
        instructions = new ArrayList<>();
        instructions.add(SETR + " [romanNumber] - set number to convertion");
        instructions.add(GETD + " - convert previously sended number");
        instructions.add(HELP + " - get instruction");
        instructions.add(EXIT + " - disconect from serwer");
        instructions.add("Response codes");
        instructions.add(OK + "  response for requests without return statement");
        instructions.add(DECIMAL_RESULT + " returning converted number ");
        instructions.add(MISSING_ARGUMENT + " Missing argument of SETR");
        instructions.add(WRONG_ROMAN_FORMAT + "sended number is not valid roman number");
        instructions.add(UNKNOWN_COMMAND + "unknown command ");
        instructions.add(HELP_ + " with this code every line of instruction set will started ");
        instructions.add(HELP_END + "end off help instructions");
    }
    
    /**Returning list of server commands with description, each String is one line
     @return ArrayList of commands*/
    public ArrayList<String> getInstructions(){
        return instructions;
    }
}
    
    
    
 