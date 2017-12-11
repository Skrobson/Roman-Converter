package romanconverter.database;
import java.sql.*;
import java.time.LocalDateTime;


/**
 * Provides acces to database  and few insert, select methods
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class HistoryDAO  {
    /**Connection with database */
    private Connection connection = null;
    

    
    /**Connect with database
     *@param dbConnection java.sql.Connection established connection with database
     */
    public HistoryDAO(Connection dbConnection){
        connection = dbConnection;
    }
    
     /**
     * Updating table CorrectConversions 
     * @param ip user ip
     * @param romanNumber converted roman number
     * @param result result of convertion
     * @throws SQLException
     */
    public void updateCorrectConverions(String ip, String romanNumber, Integer result) throws SQLException {

    }

    /**
     * Updateting table IncorectConversions
     * @param ip
     * @param romanNumber
     * @throws SQLException
     */
    public void updateIncorrectConverions(String ip, String romanNumber) throws SQLException {
                    LocalDateTime todayLocalDateTime = LocalDateTime.now();
            java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf( todayLocalDateTime );
            
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO incorrectConversions VALUES(?,?,?)");
            preparedStatement.setString(1, ip);
            preparedStatement.setString(2, romanNumber);
            preparedStatement.setTimestamp(3,sqlTimestamp);
            preparedStatement.executeUpdate();
    }

}
