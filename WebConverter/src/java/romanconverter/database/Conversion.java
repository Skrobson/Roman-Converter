
package romanconverter.database;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Bean for correct conversion
 * @Skrobol Bartłomiej
 * @version 1.0
 */
public class Conversion implements Serializable{
    
    private String ip;
    
    private String romanNumber;
    
    private int result;
    
    private Timestamp timestamp;
    
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
