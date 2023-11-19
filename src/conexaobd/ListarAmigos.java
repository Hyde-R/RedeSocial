package conexaobd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;

import model.entities.Usuario;


public class ListarAmigos extends ConexaoPostgre {

    private static final String QUERY_AMIGOS = "SELECT CASE " +
    	    "WHEN id_usuario1 = ? THEN id_usuario2 " +
    	    "ELSE id_usuario1 " +
    	    "END AS nome_amigo " +
    	    "FROM tbamizade " +
    	    "WHERE id_usuario1 = ? OR id_usuario2 = ?";

    public void listarAmigos(String usuarioLogado) throws SQLException {
        System.out.println(QUERY_AMIGOS);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_AMIGOS)) {
            preparedStatement.setString(1, usuarioLogado);
            preparedStatement.setString(2, usuarioLogado);
            preparedStatement.setString(3, usuarioLogado);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()) {
            	String nomeAmigo = rs.getString("nome_amigo");
            	System.out.println(nomeAmigo);
            }
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }
    
	
    
}