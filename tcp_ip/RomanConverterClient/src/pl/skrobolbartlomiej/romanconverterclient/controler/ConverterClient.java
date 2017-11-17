package pl.skrobolbartlomiej.romanconverterclient.controler;

import pl.skrobolbartlomiej.romanconverterclient.protocol.ServerStream;
import java.io.IOException;
import java.net.*;


import pl.skrobolbartlomiej.romanconverterclient.protocol.*;
import static pl.skrobolbartlomiej.romanconverterclient.protocol.RequestProtocol.*;
import static pl.skrobolbartlomiej.romanconverterclient.protocol.ResponseProtocol.*;
import pl.skrobolbartlomiej.romanconverterclient.view.ConsoleView;

/**
 * Provides connection and comunication with server
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class ConverterClient {
    
    /**Connection with server*/
    private Socket socket;
    
    /**Allows comunication with server */
    private ServerStream stream;
    
    /**Allows comunication with user */
    private ConsoleView view;
    
    /**Roman number retrieved from user and sended to server */
    private String numberToConvert;
    
    /**Flag signalized if client should continue work*/
    private boolean continueService = true;
    
    /**Construct new Converter client
     @param address server address
     @param port server port
     @throws IOException when is unnable to connect to server or when is unnable to create buffered stream*/
    public ConverterClient(InetAddress address, int port) throws IOException{
        socket = new Socket(address,port);
        
        view = new ConsoleView();
        
        stream = new ServerStream(socket);
    }
    
    
    /**Star client, worked in loop*/
    public void run() {
        Message response = null;
        
        try {
            do{
                response = stream.reciveMessage();
                if(response!=null){
                    processResponse(response);
                }
                else{
                    continueService = false;
                    view.printMessage("Server is disconected");
                }
            }while(continueService);
            
            
        } catch (IOException ex) {
            view.showErrorMessage(ex.getMessage());
        }
        finally{
            view.printMessage("Client ends work");
        }
        
        
    }
    
    /**Process response from server
     @param response message from server*/
    private void processResponse(Message response) {
       String responseType = response.getType();
       
       switch(responseType){
           case HELLO:
               if(response.hasPayload()){
                    view.printMessage(response.getPayload());
                    retrieveAndSendNumber();
               }
               break;
           case OK:
               stream.sendMessage(new Message(GETD));
               break;
           case DECIMAL_RESULT:
               if(response.hasPayload()){
                   view.printResult(numberToConvert, response.getPayload());
                   stream.sendMessage(new Message(EXIT));
                   continueService = false;
               }
               else{
                   view.showErrorMessage("Server responses without converted number");
                   retrieveAndSendNumber();
               } 
               break;
           case WRONG_ROMAN_FORMAT: 
               view.showErrorMessage(numberToConvert + " is not valid Roman Number");
               retrieveAndSendNumber();
               break;
           case MISSING_ARGUMENT:
               stream.sendMessage(new Message(SETR, numberToConvert));
               break;
           default:
               continueService = false;
               break;
          
       }
       
       
    }
    
    /**Retriving roman number to convert from user from console view and
     send its to server
     */
    private void retrieveAndSendNumber(){
        view.printMessage("Please type roman number");
        numberToConvert = view.retrieveNumber();
        
        stream.sendMessage(new Message(SETR , numberToConvert));
    }
    
}
