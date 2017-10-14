/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romanconverter;

import java.io.IOException;

/**
 *
* @author Skrobol Bartłomiej
 * @version 1.0
 */
public class ConventerMain {

    /**
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controler controler = null;
        if(args.length > 0){
            controler = new Controler(args[0]);
        }
        else{
            controler = new Controler();
        }
        
        controler.run();           
    }
    
}
