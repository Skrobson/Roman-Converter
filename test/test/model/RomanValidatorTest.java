
package test.model;


import org.junit.*;
import java.util.*;

import romanconventer.model.RomanNumberValidator;
import org.junit.Assert;



/**
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class RomanValidatorTest {
  
    private RomanNumberValidator validator = new RomanNumberValidator();
    static private List<String> validNumbersList;
    static private List<String> invalidNumbersList;

    @BeforeClass
    static public void prepareValidNumbersList(){
        validNumbersList = new LinkedList<>();
        validNumbersList.add("I" );
        validNumbersList.add("IV" );
        validNumbersList.add("III" );
        validNumbersList.add("VI" );
        validNumbersList.add("X" );
        validNumbersList.add("L" );
        validNumbersList.add("XL" );
        validNumbersList.add("C" );
        validNumbersList.add("CX" );
        validNumbersList.add("D");
        validNumbersList.add("m");
        validNumbersList.add("mmm");
        validNumbersList.add("CMXCIX");
        
    }
    
    @BeforeClass
    static public void prepareInvalidNumbersList(){
        invalidNumbersList = new LinkedList<>();
        invalidNumbersList.add("IIII" );
        invalidNumbersList.add("IIV" );
        invalidNumbersList.add("VIIII" );
        invalidNumbersList.add("VX" );
        invalidNumbersList.add("XVV" );
        invalidNumbersList.add("VL" );
        invalidNumbersList.add("XIL" );
        invalidNumbersList.add("CCCC" );
        invalidNumbersList.add("LL" );
        invalidNumbersList.add("MDM");
        invalidNumbersList.add("mA");
        invalidNumbersList.add("mmmm");
        invalidNumbersList.add("AAAAAAA");
        
    }
    
    @Test
    public void invalidNumberFormat(){
        
        
        
        for(String number : invalidNumbersList){
             Assert.assertFalse(number , validator.checkNumber(number));
        }
    }
    
    
    
    @Test
    public void validNumberFormat(){
                    
        for(String number : validNumbersList){
            Assert.assertTrue(number, validator.checkNumber(number));
            
        }
    }
 
}
