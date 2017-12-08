package romanconverter.database;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides connection with database and few high-level methods
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class DatabaseConnection {
    /**Connection with database */
    private Connection connection = null;
    
    /**The object used for executing a static SQL statement and returning the results it produces.*/
    private Statement statement;
    
    /**Connect with database*/
    public DatabaseConnection(){
        
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // z jakiegos pliku konf
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "lab", "lab");

            

//todo error screen
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFound exception: " + cnfe.getMessage());
        } 
        
    }
    
    public void updateHistory(String ip, String romanNumber, int result) throws SQLException{
       
        try {
            LocalDateTime todayLocalDateTime = LocalDateTime.now();
            java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf( todayLocalDateTime );
            
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CorrectlyConversions VALUES(?,?,?,?)");
            preparedStatement.setString(1, ip);
            preparedStatement.setString(2, romanNumber);
            preparedStatement.setInt(3,result);            
            preparedStatement.setTimestamp(4,sqlTimestamp);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            //todo error screen
        }
    }

}
