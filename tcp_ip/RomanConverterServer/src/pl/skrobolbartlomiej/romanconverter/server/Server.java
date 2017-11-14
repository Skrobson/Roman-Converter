package pl.skrobolbartlomiej.romanconverter.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import pl.skrobolbartlomiej.romanconverter.model.RomanNumberConverter;

/**
 *
 * @author Skrobol Bartłomiej
 */
public class Server {
    /**
     * port number
     */
    final private int PORT = 8888;

    /**
     * field represents the socket waiting for client connections
     */
    private ServerSocket serverSocket;
    
    /**Server logic */
    private RomanNumberConverter converter = new RomanNumberConverter();

    /**
     * Creates the server socket
     *
     * @throws IOException when prot is already bind
     */
    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT); 
}
    
    /**
     * Creates the server socket with given port
     *
     * @param port custom port
     * @throws IOException when prot is already bind
     */
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    
    /**Realize the server */
    public void realize(){
       Socket client; 
       try {
            while (true) {
            client= serverSocket.accept();
       
            ClientService clientService = new ClientService(converter,client);
            clientService.realize();
            client.close();
            
            
            }
        }catch (IOException e) {
            System.err.println(e.getMessage());
            }
        
        finally{       
                try {
                    serverSocket.close();                   
                } catch (IOException e) {
                    System.err.println(e.getMessage());
            }
        }
    }
}
