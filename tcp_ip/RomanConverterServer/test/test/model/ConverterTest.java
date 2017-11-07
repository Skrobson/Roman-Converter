package test.model;

import java.util.*;
import pl.skrobolbartlomiej.romanconverter.model.* ;
import org.junit.*;

/**
 * @author Skrool Bartłomiej
 * @version 1.0
 */
public class ConverterTest {
    RomanNumberConverter converter = new RomanNumberConverter();
    
    private static Map<String,Integer> testNumbers;
    
    @BeforeClass
    public static void preparetestNumbers(){
        testNumbers = new TreeMap<>();
        
        testNumbers.put("iii",3);
        testNumbers.put("ViiI",8);
        testNumbers.put("IX",9);
        testNumbers.put("XVI",16);
        testNumbers.put("XXX",30);
        testNumbers.put("LII",52);
        testNumbers.put("LXXXIX",89);
        testNumbers.put("CCLI",251);
        testNumbers.put("cd",400);
        testNumbers.put("DX",510);
        testNumbers.put("MMLXXXIX",2089);
        testNumbers.put("MMMXXX",3030);
        
    }
    
    @Test(expected = RomanNumberFormatException.class)
    public void exceptionTest()throws RomanNumberFormatException{
        String testNr = "wrong"; 
        converter.convert(testNr);     
    }
    
    
    @Test
    public void validNumbers(){
        
        
        
        for(String romanNumber : testNumbers.keySet()){
            int convertedNr = 0;
            try{
                convertedNr = converter.convert(romanNumber);
            }
            catch(RomanNumberFormatException e){
                
            }
            Assert.assertEquals(romanNumber, (int)testNumbers.get(romanNumber), convertedNr);
        }
    }
}

