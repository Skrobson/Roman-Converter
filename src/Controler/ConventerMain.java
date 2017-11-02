
package Controler;


/**
 *
 * @author Skrobol Bartłomiej
 * @version 2.0
 * Main class, checks program arguments, run controler  
 */
public class ConventerMain {

    /**
     * Main function of application
     * Cheks program arguments and run controler
     * @param args the command line argument should be roman number
     */
    public static void main(String[] args) {
        
        Controler controler = new Controler();
            
       if(args.length == 1){      
            controler.run(args[0]);
        }
       else{
            controler.run();
        }              
    }
    
}
