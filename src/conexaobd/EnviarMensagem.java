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


public class EnviarMensagem extends ConexaoPostgre {

    private static final String INSERT_MENSAGEM = "INSERT INTO tbmensagem (id_usuario_origem, id_usuario_destino, conteudo) VALUES (?, ?, ?)";

    public void enviarMensagemBanco(String usuarioLogado, String amigo, String conteudo) throws SQLException {
        System.out.println(INSERT_MENSAGEM);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MENSAGEM)) {
            preparedStatement.setString(1, usuarioLogado);
            preparedStatement.setString(2, amigo);
            preparedStatement.setString(3, conteudo);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }
    
    
}