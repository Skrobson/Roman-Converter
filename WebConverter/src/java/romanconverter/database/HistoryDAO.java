
package romanconverter.database;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Provides acces to database  and few insert, select methods
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class HistoryDAO  {
    /**Connection with database */
    private Connection connection = null;
    

    
    /**Sets connection field
     *@param dbConnection java.sql.Connection established connection with database
     */
    public HistoryDAO(Connection dbConnection){
        connection = dbConnection;
    }
    
     /**
     * Updating history  
     * @param ip user ip
     * @param romanNumber converted roman number
     * @param result result of convertion
     * @throws SQLException if a database access error occurs,
     */
    public void updateCorrectConverions(String ip, String romanNumber, Integer result) throws SQLException {
        LocalDateTime todayLocalDateTime = LocalDateTime.now();
        java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf( todayLocalDateTime );
            
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO history VALUES(?,?,?,?)");
        preparedStatement.setString(1, ip);
        preparedStatement.setString(2, romanNumber);
        preparedStatement.setInt(3, result);
        preparedStatement.setTimestamp(4,sqlTimestamp);
        preparedStatement.executeUpdate();
    }

    /**
     * Updating history
     * @param ip user ip
     * @param romanNumber converted roman number
     * @throws SQLException if a database access error occurs,
     */
    public void updateIncorrectConverions(String ip, String romanNumber) throws SQLException {
        LocalDateTime todayLocalDateTime = LocalDateTime.now();
        java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf( todayLocalDateTime );
            
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO history(ip,romanNumber,when) VALUES(?,?,?)");
        preparedStatement.setString(1, ip);
        preparedStatement.setString(2, romanNumber);
        preparedStatement.setTimestamp(3,sqlTimestamp);
        preparedStatement.executeUpdate();
    }
    
    /**
     * Retrieves the List of correct conversions
     * @return List of correctly conversions
     * @throws SQLException if a database access error occurs,
     */
    public List<Conversion> getCorrectConversions() throws SQLException{
        List<Conversion> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM history where result is not null  order by when desc ");
        while(set.next()){
            Conversion conversion = new Conversion();
            conversion.setIp(set.getString("ip"));
            conversion.setRomanNumber(set.getString("romanNumber"));
            conversion.setResult(set.getInt("result"));
            conversion.setTimestamp(set.getObject("when", Timestamp.class ));
            list.add(conversion);
        }
        
        return list;
    }
    
    /**
     * Retrieves the List of incorrect conversions
     * @return List of correctly conversions
     * @throws SQLException if a database access error occurs,
     */
    public List<Conversion> getIncorreConversions() throws SQLException{
        List<Conversion> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM history where result is null  order by when desc ");
        while(set.next()){
            Conversion conversion = new Conversion();
            conversion.setIp(set.getString("ip"));
            conversion.setRomanNumber(set.getString("romanNumber"));
            conversion.setTimestamp(set.getObject("when", Timestamp.class ));
            list.add(conversion);
        }
        
        return list;
    }
}
