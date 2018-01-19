package skrobol.romanandroid;

import java.util.*;
import skrobol.romanandroid.model.* ;
import org.junit.*;

/**
 * The type Converter test.
 *
 * @author Skrool Bartłomiej
 * @version 1.0
 */
public class ConverterTest {
    /**
     * The Converter.
     */
    RomanNumberConverter converter = new RomanNumberConverter();
    
    private static Map<String,Integer> testNumbers;

    /**
     * Prepare test numbers.
     */
    @BeforeClass
    public static void prepareTestNumbers(){
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

    /**
     * Exception test.
     *
     * @throws RomanNumberFormatException the roman number format exception
     */
    @Test(expected = RomanNumberFormatException.class)
    public void exceptionTest()throws RomanNumberFormatException{
        String testNr = "ixxse"; 
        converter.convert(testNr);  
    }

    /**
     * Null string test.
     *
     * @throws RomanNumberFormatException the roman number format exception
     */
    @Test(expected = RomanNumberFormatException.class)
    public void nullStringTest()throws RomanNumberFormatException{
        String testNr = null; 
        converter.convert(testNr);  
    }

    /**
     * Emptyt string test.
     *
     * @throws RomanNumberFormatException the roman number format exception
     */
    @Test(expected = RomanNumberFormatException.class)
    public void emptytStringTest()throws RomanNumberFormatException{
        String testNr = ""; 
        converter.convert(testNr);  
    }

    /**
     * Valid numbers.
     */
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

