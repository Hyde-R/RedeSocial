package application;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.entities.Usuario;


public class RedeSocialGUI extends JFrame {
	
    private JPanel painel = new JPanel();
    private JButton jButtonCadastrar = new JButton("Cadastrar");
    private JButton jButtonListarUsuarios = new JButton("Listar Usuários");
    private JButton jButtonExcluirUsuario = new JButton("Excluir Usuário");

    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    ArrayList<Usuario> usuarios = new ArrayList<>();
    Usuario user = new Usuario(usuarios);
    
    public RedeSocialGUI(){
        this.setTitle("NewtWork");
        this.setSize(400,400);
        painel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        painel.setBackground(new Color(102, 0, 211));
        JTextArea textArea = new JTextArea(10, 30); // 10 linhas por 30 colunas
        textArea.setEditable(false); // Impede a edição do texto pelo usuário
        JScrollPane scrollPane = new JScrollPane(textArea); // Adiciona uma barra de rolagem ao JTextArea

        jButtonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Botão de Cadastrar Usuários funcionou!");
                try {
					user.cadastrarUsuario();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        jButtonListarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Botão de Listar Usuários funcionou!");
                user.listarUsuario(usuarios, textArea);
            }
        });
        
        jButtonExcluirUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Botão de Excluir Usuários funcionou!");
                user.apagarUsuario(usuarios);
            }
        });

        painel.add(jButtonCadastrar);
        painel.add(jButtonListarUsuarios);
        painel.add(jButtonExcluirUsuario);
        painel.add(scrollPane);

        this.getContentPane().add(painel);
        this.setLocationRelativeTo(null); // Centralizar janela
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true); // Exibir janela
    }

    public static void main(String[] args) {
        new RedeSocialGUI();
    }
}
