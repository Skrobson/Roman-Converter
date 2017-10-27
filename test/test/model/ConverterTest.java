
package test.model;

import Model.*;

import org.junit.*;
/**
 *
 * @author Skrool Bartłomiej
 */
public class ConverterTest {
    RomanNumberConverter converter = new RomanNumberConverter();
    
    @Test(expected = RomanNumberFormatException.class)
    public void exceptionTest()throws RomanNumberFormatException{
        String testNr = "wrong"; 
        converter.convert(testNr);     
    }
    
    
    @Test
    public void validNumber(){
        String validNr = "XXXIII";
        
        try {
            Assert.assertEquals(33, converter.convert(validNr));
        } catch (RomanNumberFormatException ex) {
           
        }
    }
}
