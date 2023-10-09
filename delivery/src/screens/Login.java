package screens;

import components.Text;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Login extends JPanel {
    ArrayList<User> usuarios = new ArrayList<User>();
    User loggedUser;
    TextField nome = new TextField();
    TextField cpf = new TextField();
    Text nomeLabel = new Text("nome", 250,180,100,20, Color.CYAN);
    Text cpfLabel = new Text("cpf", 250,280,40,20, Color.CYAN);
    Text title = new Text("DELIVERY", 280,100,250,60, Color.CYAN, Font.BOLD, 50);

    public User getLoggedUser(){
        return loggedUser;
    }

    public void setUsuarios(ArrayList<User> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<User> getUsuarios() {
        return usuarios;
    }

    public Login(ArrayList<User> usuarios, Color color){
        this.usuarios = usuarios;
        nome.setBounds(250,200, 300,22);
        cpf.setBounds(250,300, 300,22);
        add(nomeLabel);
        add(cpfLabel);
        add(nome);
        add(cpf);
        add(title);
        setBackground(color);
        setLayout(null);
        setVisible(true);
    }

    public boolean logar(){
        for (User usuario: usuarios){
            if(Objects.equals(usuario.getCPF(), cpf.getText()) && Objects.equals(usuario.getName(), nome.getText())){
                this.loggedUser = usuario;
                return true;
            }
        }
        return false;
    }
}
