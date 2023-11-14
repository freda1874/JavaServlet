/* File: DataSource.java
 * Author: Lei Luo
 * Date: 2023
 * Description: to build database connection with peertutor
 * References:
 * Ram N. (2013).  Data Access Object Design Pattern or DAO Pattern [blog] Retrieved from
 * http://ramj2ee.blogspot.in/2013/08/data-access-object-design-pattern-or.html
 */

package dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides database connection functionality for the PeerTutor application.
 * This class handles the creation and management of connections to the database.
 * It follows the Singleton pattern to ensure only one connection is used throughout the application.
 * @author: Lei Luo
 * @version 1.0
 * @since JDK 11
 */
public class DataSource {
 
    private Connection connection = null;
 
    private final String url = "jdbc:mysql://localhost:3306/peertutor?useSSL=false&allowPublicKeyRetrieval=true";
    private final String username = "root";
    private final String password = "new_password_here";
 /**
     * Constructs a DataSource object. 
     * Initializes database connection parameters but does not establish the connection.
     */
    
    public DataSource() {
    }

   /**
     * Creates and returns a database connection.
     * This method ensures that only one connection is active at any given time.
     * If a connection already exists, it will not create a new one.
     * 
     * @return An active database connection. If a connection was previously created, the existing connection is returned.
     */
    public Connection createConnection() {
        try {
            if (connection != null) {
                System.out.println("Cannot create new connection, one exists already");
            } else {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
    
      

}
