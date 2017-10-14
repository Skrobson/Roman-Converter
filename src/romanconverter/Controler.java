/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romanconverter;

/**
* @author Skrobol Bartłomiej
 * @version 1.0
 * 
 * Application controler.
 * Works in loop while user type a roman number in valid format
 */
public class Controler {
    private ConsoleView view = new ConsoleView();
    private RomanNumberConverter converter = new RomanNumberConverter();
    private String numberToConvert = null;

    public Controler(String romanNumber){
        numberToConvert = romanNumber;
    }
    
    public Controler(){
        
    }
    
    /**
     * run controler in loop while user type romanNumber in valid format
     */
    public void run(){
        
        Integer result = null;
        
        do{
            if(numberToConvert == null){
                view.printMessage("Please type roman number");
                numberToConvert = view.getInput();
            }
            try{
           result = converter.convert(numberToConvert);
        }
        catch(RomanNumberFormatException exception){
            view.showErrorMessage(exception.getMessage());
            numberToConvert = null;
        }
            
        }while(result == null);
        
        view.printResult(numberToConvert,result);        
    }
            
}

