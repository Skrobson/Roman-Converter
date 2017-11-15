package pl.skrobolbartlomiej.romanconverter.model;

import java.util.regex.*;

/**
 * Responsible for validation roman digits expression
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class RomanNumberValidator {
    /**regular expresion for validation roman number format*/
    private final String ROMAN_DIGITS = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    
    /**A compiled representation of a regular expression. */
    private final Pattern PATTERN = Pattern.compile(ROMAN_DIGITS);
    
 
    /**i
     * Checks is string have valid roman number format
     * @param number roman number to validation
     * @return true if string is valid roman number
     */
    public boolean checkNumber(String number){
        if(number== null || number==""){
            return false;
        }
        else{
            Matcher matcher = PATTERN.matcher(number.toUpperCase());
            return matcher.matches();
            
        }
    }
}