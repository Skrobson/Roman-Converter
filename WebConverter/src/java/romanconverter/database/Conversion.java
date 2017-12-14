
package romanconverter.database;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Bean for conversion history
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class Conversion implements Serializable{
    /**user ip */
    private String ip;
    
    /**roman number sended to convertion*/
    private String romanNumber;
    
    /**result of convertion
     0 if roman number is invalid*/
    private int result = 0;
    
    /**Inform when conversion was done */
    private Timestamp timestamp;
    
    /**Empty constructor */
    public Conversion(){
    }
    
    public void setIp(String ip){
        this.ip = ip;
    }
    
    public String getIp(){
        return ip;
    }
    
    public void setRomanNumber(String romanNumber){
        this.romanNumber = romanNumber;
    }
    
    public String getRomanNumber(){
        return romanNumber;
    }
    
    public void setResult(int result){
        this.result = result;
    }
    
    public int getResult(){
        return result;
    }
    
    public void setTimestamp(Timestamp timestamp){
        this.timestamp = timestamp;
    }
    
    public Timestamp getTimestamp(){
        return timestamp;
    }
}
