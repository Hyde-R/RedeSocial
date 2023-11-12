package model.entities;

import java.awt.TextField;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.exceptions.DomainException;

public class Usuario {
	
	private String nome;
	private String email;
	private String senha;
	private String naturalidade;
	private String nascimento;
	private String genero;
	
	Scanner sc = new Scanner(System.in);
	
	ArrayList<Usuario> usuarios = new ArrayList<>();
	
	public Usuario() {
		
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
	
	public void cadastrarUsuario() {
		nome = JOptionPane.showInputDialog(null, "Digite seu nome");
		email = JOptionPane.showInputDialog(null, "Digite seu e-mail");
		senha = JOptionPane.showInputDialog(null, "Digite sua senha");
		naturalidade = JOptionPane.showInputDialog(null, "Digite sua naturalidade");
		nascimento = JOptionPane.showInputDialog(null, "Digite sua data de nascimento");
		genero = JOptionPane.showInputDialog(null, "Digite seu gênero (M / F)");
		
		Usuario user = new Usuario(nome, email, senha, naturalidade, nascimento, genero);
		usuarios.add(user);
	}
	
	public void listarUsuario(ArrayList<Usuario> usuarios, JTextArea textArea) {
	    textArea.setText("");
	    if (usuarios.isEmpty()) {
	        textArea.append("Nenhum usuário cadastrado ainda.");
	    } else {
	    	for (Usuario user : usuarios) {
		        textArea.append("Nome: " + user.getNome() + "\nE-mail: " + user.getEmail() + "\n\n");
		    }
	    }
	    
	}
	
	public void apagarUsuario(ArrayList<Usuario> usuarios) throws DomainException {
		nome = JOptionPane.showInputDialog(null, "Digite o nome do usuário a ser excluído");
		Usuario usuarioExcluir = null;
		
		for(Usuario user : usuarios) {
			if(nome.equals(user.getNome())) {
				usuarioExcluir = user;
			}
		}
		
		if(usuarioExcluir != null) {
			usuarios.remove(usuarioExcluir);
			JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
		}
		else {
			throw new DomainException("Usuário não foi encontrado e portanto não pode ser excluído!");
		}
	}
}
