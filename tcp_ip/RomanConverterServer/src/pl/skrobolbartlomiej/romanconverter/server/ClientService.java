package pl.skrobolbartlomiej.romanconverter.server;

import java.io.IOException;
import java.net.Socket;
import pl.skrobolbartlomiej.romanconverter.model.*;
import pl.skrobolbartlomiej.romanconverter.protocol.*;
import static pl.skrobolbartlomiej.romanconverter.protocol.RequestProtocol.*;
import static pl.skrobolbartlomiej.romanconverter.protocol.ResponseProtocol.*;
/**
 * Handle and process request from client 
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class ClientService {
    /**Server logic */
    private RomanNumberConverter converter;
    
    /**Roman number for convertion */
    private String romanNumber = null;
    
    /**Flag to signalize is roman number is seted and ready to convert */
    private boolean isNumberSeted = false;
    
    /**
     *signal when server ends servise
     */
    private boolean continueService = true;
    
    /**
     * socket representing connection to the client
     */
    Socket socket;
    
    /**Comunication with client */
    ClientStream stream;
    
     /**
     * The constructor of instance of the ClientService class. 
     *
     * @param converter instance of model
     * @param socket socket representing connection to the client
     * 
     */
    public ClientService(RomanNumberConverter converter, Socket socket)throws IOException{
        this.converter = converter;
        this.socket = socket;
        stream = new ClientStream(socket);
    }
    
     /**
     * Realizes the service
     */
    void realize(){
       
        stream.sendMessage(new Message(HELLO,"Welcome to Roman Converter Server"));
        
        Message request;
        
        do{
            if(socket.isConnected()){
                try{
                    request=stream.reciveMessage();
                    handleRequest(request);
                }
                catch(IOException ex){
                    this.continueService = false;
                    System.err.println(ex.getMessage());
                }
            }
            else{
                continueService = false;
            }
        }while(this.continueService);
        
        
    }
    
    /**Handle requests 
     *@param request String with Request code and payload 
     *@return true if client exit*/
    private void handleRequest(Message request){
        if(request == null)
        continueService = false;
        
        else{
        String command = request.getType();
        
        switch(command){
            case SETR:
                setRomanNumber(request);
                break;
            case GETD:
                convert();
                break;
            case HELP:
                help();
                break;
            case EXIT:
               this.continueService = true;
                break;
            default:
                stream.sendMessage(new Message(UNKNOWN_COMMAND,command));
                break;
        }
        }
    }
    
    private void setRomanNumber(Message request){
        
        if(request.hasPayload()){
                romanNumber=request.getPayload();
                System.out.println(romanNumber);
                isNumberSeted = true;
                stream.sendMessage(new Message(OK)); 
                }
                else{
                    stream.sendMessage(new Message(MISSING_ARGUMENT));
                }
        
    }    
    
    private void convert(){
        if (isNumberSeted){
            try {
                int result= converter.convert(romanNumber);
               
                stream.sendMessage(new Message(
                        DECIMAL_RESULT,Integer.toString(result)));
                } catch (RomanNumberFormatException ex) {
                    stream.sendMessage(new Message(WRONG_ROMAN_FORMAT,ex.getMessage()));
                    }
            finally{
                isNumberSeted = false;
                }
            }
                
        else{
            stream.sendMessage(new Message(
                    MISSING_ARGUMENT,"First use SETR with argument"));
          }

    }
    
    private void help(){
        HelpInstruction help = new HelpInstruction();
        
        help.getInstructions().forEach((instruction) -> {
            stream.sendMessage(new Message(HELP_,instruction));
        });
        stream.sendMessage(new Message(HELP_END));
    }
}
