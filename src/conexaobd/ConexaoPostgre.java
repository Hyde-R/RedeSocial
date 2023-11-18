package conexaobd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoPostgre {
	 public final String url = "jdbc:postgresql://localhost/RedeSocial";
	 public final String user = "postgres";
	 public final String password = "22194";
	 
	    public static void printSQLException(SQLException ex) {
	            for (Throwable e: ex) {
	                if (e instanceof SQLException) {
	                    e.printStackTrace(System.err);
	                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                    System.err.println("Message: " + e.getMessage());
	                    Throwable t = ex.getCause();
	                    while (t != null) {
	                        System.out.println("Cause: " + t);
	                        t = t.getCause();
	                    }
	                }
	            }
	        }
	
	}
