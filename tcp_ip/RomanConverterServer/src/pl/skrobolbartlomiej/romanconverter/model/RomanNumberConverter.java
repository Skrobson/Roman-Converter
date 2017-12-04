package pl.skrobolbartlomiej.romanconverter.model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Convert roman number  to arabic number
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class RomanNumberConverter {
    
    /**Dictionary for roman digits*/
    private final Map<Character, Integer> DIGITS;
    {
        DIGITS = new HashMap<>();
        DIGITS.put('M', 1000);
        DIGITS.put('D', 500);
        DIGITS.put('C', 100);
        DIGITS.put('L', 50);
        DIGITS.put('X', 10);
        DIGITS.put('V', 5);
        DIGITS.put('I', 1);
    }
    
        /**regular expresion for validation roman number format*/
    private final String ROMAN_DIGITS = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    
    /**A compiled representation of a regular expression. */
    private final Pattern PATTERN = Pattern.compile(ROMAN_DIGITS);
    
 
    /**
     * Checks is string have valid roman number format
     * @param number roman number to validation
     * @return true if string is valid roman number
     */
    protected boolean validNumber(String number){
        if(number== null || number.equals("")){
            return false;
        }
        else{
            Matcher matcher = PATTERN.matcher(number.toUpperCase());
            return matcher.matches();
            
        }
    }
    
    /**
     * Convert roman number string to arabic number
     * @param romanNumber representetion of roman number
     * @return arabic number as int
     * @throws RomanNumberFormatException if romanNumber is in invalid format
     */
    public int convert(String romanNumber) throws RomanNumberFormatException{
        
        String number = romanNumber.toUpperCase();
        
        if(validNumber(number))
            return calculateFromRoman(number);
        
        else
            throw new RomanNumberFormatException("Invalid number format");
    }  
    
    /**
     * calculate a number from string of roman digits after validation
     * @param number in valid format
     * @throws RomanNumberFormatException if number is in invalid format
     */
    private int calculateFromRoman(String number) throws RomanNumberFormatException{
        
       int result = 0;
       int previousDigit = 0;
       int digitRepetition = 0;
       
       for(Character romanDigit : number.toCharArray()){

           if(romanDigit == null)          
               throw new RomanNumberFormatException("unexceptet character in roman number");
           
           int digit = DIGITS.get(romanDigit);
           
           if(digit == previousDigit )
               ++digitRepetition;
           
           else if(digit < previousDigit){
               result += previousDigit * digitRepetition;
               previousDigit = digit;
               digitRepetition = 1;
           }
           else {
               previousDigit = digit - previousDigit;
               digitRepetition = 1;
           }               
       }
       result += previousDigit * digitRepetition;
       
       return result;
    }
}