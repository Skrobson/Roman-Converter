/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romanconverter;

import java.util.regex.*;

/**
 *
 * @author Skrobol Bartłomiej
 * @version 1.0
 */

/**
 * Responsible for validation roman digits expression
 */
public class RomanNumberValidator {
    
    final private String romanRegex = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    //final private String romanRegex = "I?{,3}V{0,3}X{0,3}L{0,3}C{0,3}D{0,3}M{0,3}";
    
    private Pattern pattern;
    
   
    
    public RomanNumberValidator(){
        
        pattern = Pattern.compile(romanRegex);
    }
    
    /**
     * Checks is string have only roman digits
     * @param digits to validation
     * @return true if string is valid roman number
     */
    public boolean checkNumber(String digits){
        
        String upperCaseDigits = digits.toUpperCase();
        Matcher matcher = pattern.matcher(upperCaseDigits);
        return matcher.matches();
    }
}
