package application;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import conexaobd.CadastrarUsuario;
import model.entities.Usuario;

public class TesteLuan {

	public static void main(String[] args) throws SQLException {

		Usuario user = new Usuario();
		user.logarUsuario();
	}

}
