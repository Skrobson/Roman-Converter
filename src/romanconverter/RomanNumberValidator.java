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
 * Responsible for validation roman digits expression
 */
public class RomanNumberValidator {
    /**regular expresion for validation roman number format*/
     private final String romanRegex = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    
    /**A compiled representation of a regular expression. */
    private final Pattern pattern = Pattern.compile(romanRegex);
    
 
    /**i
     * Checks is string have valid roman number format
     * @param number roman number to validation
     * @return true if string is valid roman number
     */
    public boolean checkNumber(String number){
        
        Matcher matcher = pattern.matcher(number.toUpperCase());
        return matcher.matches();
    }
}
