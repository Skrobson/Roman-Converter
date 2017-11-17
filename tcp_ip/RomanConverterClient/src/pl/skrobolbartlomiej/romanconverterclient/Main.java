package pl.skrobolbartlomiej.romanconverterclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.skrobolbartlomiej.romanconverterclient.controler.ConverterClient;
import pl.skrobolbartlomiej.romanconverterclient.view.ConsoleView;


/**
 * Class with main method,  
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class Main {

    /**
     * Main method, connect to server with properties from config.xml or arguments
     * @param args first argument is a server address, second is a port number
     */
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        int port = 0;
        String address = "" ;
       
        if(args.length>2){
            address = args[0];
            
            try{
                port = Integer.parseInt(args[2]);
            }
            catch(NumberFormatException ex){
                view.showErrorMessage(args[2] + "is not valid port ");
            }
        }
        else{
            Properties properties = new Properties();
            
            try (FileInputStream in = new FileInputStream("config.xml")) {
                properties.loadFromXML(in);
                address = properties.getProperty("address");
                port = Integer.parseInt(properties.getProperty("port"));
            } catch (IOException  ex) {
                view.showErrorMessage(ex.getMessage());           
            }
            catch(NumberFormatException ex){
                view.showErrorMessage(properties.getProperty("port") + "is not valid port \n"
                        + "please check your config.xml file");
            }
        }
        
        try {
            ConverterClient client = new ConverterClient(InetAddress.getByName(address), port);
            client.run();
        } catch ( IOException ex) {
            view.showErrorMessage("error occurs while creating a connection to the remote host for a remote method call" + ex.getMessage());
        }
        
        
    }
    
}
