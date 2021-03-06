package pl.skrobolbartlomiej.romanconverterclient.protocol;

import java.net.Socket;
import java.io.*;
import pl.skrobolbartlomiej.romanconverterclient.protocol.*;

/**
 *  Allows to comunicate with server using Message's
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class ServerStream {
     /**
     * socket representing connection to the client
     */
    private Socket socket;
    /**
     * buffered input character stream
     */
    private BufferedReader input;
    /**
     * Formatted output character stream
     */
    private PrintWriter output;
    
    
    /**Create buffered input character stream and formatted output character stream
     @param socket connected with client
     * @throws java.io.IOException when streams cannot be open'ed
     */
    public ServerStream(Socket socket) throws IOException{
        this.socket = socket;
        
        output = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);
        
        input = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
      
    }
    
    /**Sends message to client 
     @param message to send*/
    public void sendMessage(Message message){
        output.println(message.getRawData());
    }
    /**Recive message from client
     @return Message recived from client
     @throws IOException when cannot read from input stream
     */
    public Message reciveMessage() throws IOException{
        String rawData = input.readLine();
        if(rawData==null){
            return null;
        }
        else{
            return Message.createFromRawData(rawData);
        }
    }
}
