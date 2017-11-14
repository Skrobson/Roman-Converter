package pl.skrobolbartlomiej.romanconverter;

import java.io.IOException;
import pl.skrobolbartlomiej.romanconverter.server.Server;

/**
 * Main class
 * @author SkrobolBartłomiej
 * @version 1.0
 */
public class Main {

    /**Main method, starts the server
     * @param args the command line argument can be a custom Port number
     */
    public static void main(String[] args){
        Server server;
        try{    
            if(args.length>1){
                int port = Integer.parseInt(args[1]);
                server= new Server(port);
            }
            else{
                server= new Server();
            }
        server.realize();
     
        }catch(IOException ex){
            System.err.println(ex.getMessage());    
        }
        catch(NumberFormatException ex){
            System.err.println(ex.getMessage() + "wrong port number");
        }
    }
}

