/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romanconverter;

import java.io.IOException;

import java.util.Scanner;

/**
 *
 * @author Skrobol Bartłomiej
 * @version 1
 */
public class ConventerMain {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
                 
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        RomanNumberValidator validator = new RomanNumberValidator();
         
        System.out.print(s);
        System.out.print(validator.checkNumber(s));
    }
    
}
