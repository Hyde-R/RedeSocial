package application;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.entities.Usuario;
import model.exceptions.DomainException;

public class TesteLuan {

	public static void main(String[] args) throws SQLException {
		Usuario user = new Usuario();
		String opcao = "";
		boolean testeLogin = false;
		String nomeX = "";
		String senhaX = "";
		
		opcao = JOptionPane.showInputDialog(null, "Você já possui cadastro? \n<1 - Cadastrar \n<2 - Logar");
		int opcaoX1 = Integer.parseInt(opcao);
		switch (opcaoX1) {
		case 1:
			user.cadastrarUsuario();
			break;
		}

		try {
			do {
				nomeX = JOptionPane.showInputDialog(null, "Digite seu nome");
				senhaX = JOptionPane.showInputDialog(null, "Digite sua senha");
				testeLogin = user.logarUsuario(nomeX, senhaX);
			} while (testeLogin != true);

		} catch (DomainException e) {
			e.getMessage();
		}
		opcao = JOptionPane.showInputDialog(null,
				"Agora que você logou, o que deseja fazer? \n\n<1>Adicionar amigo \n<2>Listar amigos \n<3>Enviar mensagens para um amigo"
				+ "\n<4>Excluir um amigo \n<5>Logout");
		int opcaoX2 = Integer.parseInt(opcao);
		switch (opcaoX2) {
		case 1:
			user.adicionarAmigo(nomeX);
			break;

		case 2:
			user.listarAmigos(nomeX);
			break;
			
		case 3:
			user.enviarMensagens(nomeX);
			break;

		case 4:
			user.excluirAmigo(nomeX);
			break;
			
		case 5:
			JOptionPane.showMessageDialog(null, "Deslogado. \nAté a próxima!");
		}

	}

}
