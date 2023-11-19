package model.entities;

import java.awt.TextField;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import conexaobd.AdicionarAmigo;
import conexaobd.CadastrarUsuario;
import conexaobd.ConexaoPostgre;
import conexaobd.EnviarMensagem;
import conexaobd.ExcluirAmigo;
import conexaobd.ListarAmigos;
import conexaobd.LogarUsuario;
import model.exceptions.DomainException;

public class Usuario {
	
	private String nome;
	private String email;
	private String senha;
	private String naturalidade;
	private String nascimento;
	private String genero;
	private CadastrarUsuario novoUsuario = null;
	private ConexaoPostgre cc = new ConexaoPostgre();
	private LogarUsuario login = null;
	private AdicionarAmigo aa = null;
	private ExcluirAmigo ea = null;
	private ListarAmigos la = new ListarAmigos();
	private EnviarMensagem em = new EnviarMensagem();
	
	ArrayList<Usuario> usuarios = new ArrayList<>();
	
	public Usuario() {
		this.novoUsuario = new CadastrarUsuario();
		this.login = new LogarUsuario();
		this.aa = new AdicionarAmigo();
		this.ea = new ExcluirAmigo();
		this.la = new ListarAmigos();
	}
	
	public Usuario(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario(String nome, String email, String senha, String naturalidade, String nascimento, String genero) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.naturalidade = naturalidade;
		this.nascimento = nascimento;
		this.genero = genero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	// Realizar o cadastro de um usuario
	public void cadastrarUsuario() throws SQLException {
		try {
			Date nascimentoData = null;
			nome = JOptionPane.showInputDialog(null, "Digite seu nome");
			email = JOptionPane.showInputDialog(null, "Digite seu e-mail");
			senha = JOptionPane.showInputDialog(null, "Digite sua senha");
			naturalidade = JOptionPane.showInputDialog(null, "Digite sua naturalidade");
			nascimento = JOptionPane.showInputDialog(null, "Digite sua data de nascimento (ano-mês-dia)");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			nascimentoData = dateFormat.parse(nascimento);
			genero = JOptionPane.showInputDialog(null, "Digite seu gênero (M / F)");
			Date nascimentoSql = new Date(nascimentoData.getTime());
			
			if(cc.validarUsuarioBanco(nome) == true) {
				JOptionPane.showMessageDialog(null, "O nome digitado já foi escolhido por outro usuário. Por favor, defina um novo.");
			}
			else if(cc.validarUsuarioBanco(email) == true) {
				JOptionPane.showMessageDialog(null, "O email digitado já está em uso. Por favor, selecione outro email.");
			}
			else {
				novoUsuario.inserirUsuarioBanco(nome, email, senha, naturalidade, nascimentoData, genero);
				Usuario user = new Usuario(nome, email, senha, naturalidade, nascimento, genero);
				usuarios.add(user);
			}
			
		}
		catch(ParseException e){
	        e.printStackTrace();

	    }
	}
	
	public boolean logarUsuario(String nomeX, String senhaX) throws SQLException, DomainException {
		
		try {
		    
		    boolean encontradoBanco = login.verificarUsuarioBanco(nomeX, senhaX);

		    if (encontradoBanco) {
		    	JOptionPane.showMessageDialog(null, "Bem vindo a Newtwork!");
		        return true;
		    } else {
		        JOptionPane.showMessageDialog(null, "Acesso negado! Usuário ou senha inválidos.");
		        return false;
		    }
		}
		catch(DomainException e) {
			e.getMessage();
		}
		return false;
	    
	}
	
	public void adicionarAmigo(String usuarioLogado) throws SQLException {
		try {
			String amigoNovo = JOptionPane.showInputDialog(null, "Qual o nome do amigo que deseja adicionar?");
			
			boolean encontradoBanco = cc.validarUsuarioBanco(amigoNovo);
			
			if (encontradoBanco) {
				aa.adicionarAmigoBanco(usuarioLogado, amigoNovo);
		    	JOptionPane.showMessageDialog(null, "Amigo adicionado com sucesso!");
		    } else {
		        JOptionPane.showMessageDialog(null, "Usuario não encontrado, e portanto, não foi adicionado.");
		    }
		}
		catch(DomainException e) {
			e.getMessage();
		}
		
	}
	
	public void excluirAmigo(String usuarioLogado) throws SQLException {
		try {
			String amigoExcluir = JOptionPane.showInputDialog(null, "Qual o nome do amigo que deseja excluir?");
			
			boolean encontradoBanco = cc.validarUsuarioBanco(amigoExcluir);
			
			if (encontradoBanco) {
				ea.excluirAmigoBanco(usuarioLogado, amigoExcluir);
		    	JOptionPane.showMessageDialog(null, "Amigo excluído com sucesso!");
		    } else {
		        JOptionPane.showMessageDialog(null, "Amigo não encontrado, e portanto, não foi excluído.");
		    }
		}
		catch(DomainException e) {
			e.getMessage();
		}
			
		}
	
	public void listarAmigos(String usuarioLogado) throws SQLException {
		try {
			la.listarAmigos(usuarioLogado);
		}
		catch(DomainException e) {
			e.getMessage();
		}
		
	}
	
	public void enviarMensagens(String usuarioLogado) throws SQLException {
		String amigoMensagem = JOptionPane.showInputDialog(null, "Para qual amigo deseja mandar uma mensagem?");
		boolean encontrado = cc.validarUsuarioBanco(amigoMensagem);
		try {
			if(encontrado) {
				String conteudo = JOptionPane.showInputDialog(null, "Digite a mensagem aqui");
				em.enviarMensagemBanco(usuarioLogado, amigoMensagem, conteudo);
				JOptionPane.showMessageDialog(null, "Mensagem enviada com sucesso!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Amigo não encontrado, portanto, a mensagem não foi enviada!");
			}
		}
		catch(DomainException e) {
			e.getMessage();
		}
	}
	
}
