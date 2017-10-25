/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;


/**
 *
* @author Skrobol Bartłomiej
 * @version 1.0
 */
public class ConventerMain {

    /**
     * @param args the command line argument should be roman number
     *
     */
    public static void main(String[] args) {
        
        Controler controler = new Controler();
    
        
       try{           
            controler.run(args[0]);
        }
        catch(ArrayIndexOutOfBoundsException e){
            controler.run();
        }
        
                  
    }
    
}
