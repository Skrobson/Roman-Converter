package pl.skrobolbartlomiej.romanconverter.protocol;

/**
 *Class for request's and response's
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class Message {
    /**Mesage type, protocol string*/
    String type;
    
    /**data sended with message*/
    String payload;
    
    /**If message has payload, variable will be seted to true*/
    boolean hasPayload;
    
    /**
     * create message with payload
     * @param type request o response protocol as string
     * @param payload data sended with message
     */
    public Message(String type, String payload){
        this.type = type.toUpperCase();
        this.payload = payload;
        hasPayload = true;
    }
    
    /**Create message without payload*/
    public Message(String type){
        this.type = type.toUpperCase();
        hasPayload = false;
        payload = "";
    }
    
    /**Inform about type of message
     @return request or response as string*/
    public String getType(){
        return type;
    }
    
    /**Signals if message has payload
     @return true if message has payload*/
    public boolean hasPayload(){
        return hasPayload;
    }
    
    /**Message payload, empty string if message has not have payload
     @return payload as string*/
    public String getPayload(){
        return payload;
    }
    
    /**Return raw data(type + payload)
     @return raw data*/
    public String getRawData(){
        String rawData = type + " ";
        if(hasPayload){
            rawData+= payload;
        }
        return rawData;
    }
    
    
    /**Static factory method, create message from raw data from stream
     * this method don't check correctness of raw data
     @return new Message */
    static public Message createFromRawData(String rawData){
        //
        if(rawData==null){
            
        }
        else{
            rawData = "";
        }
        String[] data = rawData.split(" " , 2);
        String type = data[0].toUpperCase();
        
        if(data.length>1){
            String payload =data[1];
            return new Message(type, payload);
        }
        return new Message(type);
    }
}
