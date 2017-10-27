
package test.model;


import org.junit.*;

import Model.RomanNumberValidator;
import org.junit.Assert;



/**
 *
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class RomanValidatorTest {
  
    RomanNumberValidator validator = new RomanNumberValidator();
    
   
    @Test
    public void invalidNumberFormat(){
        
        
        String number = "kk";
        Assert.assertFalse(number + " is wrong roman nr", validator.checkNumber(number));
    }
    
    @Test
    public void validNumberFormat(){
    
        String number = "III";
        Assert.assertTrue(validator.checkNumber(number));
    }
 
}
