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

import conexaobd.CadastrarUsuario;
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
	private LogarUsuario login = null;
	
	ArrayList<Usuario> usuarios = new ArrayList<>();
	
	public Usuario() {
		this.novoUsuario = new CadastrarUsuario();
		this.login = new LogarUsuario();
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
			nascimento = JOptionPane.showInputDialog(null, "Digite sua data de nascimento");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			nascimentoData = dateFormat.parse(nascimento);
			genero = JOptionPane.showInputDialog(null, "Digite seu gênero (M / F)");
			Date nascimentoSql = new Date(nascimentoData.getTime());
			novoUsuario.inserirUsuarioBanco(nome, email, senha, naturalidade, nascimentoData, genero);
			Usuario user = new Usuario(nome, email, senha, naturalidade, nascimento, genero);
			usuarios.add(user);
		}
		catch(ParseException e){
	        e.printStackTrace();

	    }
	}
	
	// Validar se o usuário existe e senha está correta para acessar a Rede Social
	public boolean logarUsuario() throws SQLException {
	    String nomeX = JOptionPane.showInputDialog(null, "Digite seu nome");
	    String senhaX = JOptionPane.showInputDialog(null, "Digite sua senha");
	    
	    boolean encontradoBanco = login.verificarUsuarioBanco(nomeX, senhaX);

	    if (encontradoBanco) {
	    	JOptionPane.showMessageDialog(null, "Bem vindo a Newtwork!");
	        return true;
	    } else {
	        JOptionPane.showMessageDialog(null, "Acesso negado! Usuário ou senha inválidos.");
	        return false;
	    }
	}
	
}
