package screens;

import entities.Status;
import entities.User;

import javax.swing.*;
import java.awt.*;


public class Register extends JPanel {
    private TextField nome = new TextField();
    private TextField cpf = new TextField();
    private JLabel nomeLabel = new JLabel("nome");
    private JLabel cpfLabel = new JLabel("cpf");
    private TextField x = new TextField();
    private TextField y = new TextField();
    private JLabel xLabel = new JLabel("x");
    private JLabel yLabel = new JLabel("y");
    private JLabel isAdmLabel = new JLabel("is ADM");
    private JCheckBox isAdmBox = new JCheckBox();
    private boolean isAdm = false;
    private User user;
    public void setUser(User user){
        this.user = user;
//        System.out.println(this.user.getRole());

        isAdmLabel.setVisible(admLogin());
        isAdmBox.setVisible(admLogin());
    }

    public String getNome() {
        return nome.getText();
    }

    public String getCpf() {
        return cpf.getText();
    }

    public String getXL() {
        return x.getText();
    }

    public String getYL() {
        return y.getText();
    }
    public Status getStatus(){
        System.out.println(isAdmBox.getText());
        if (isAdm){
            return Status.ADM;
        };
        return Status.USER;
    }

    public Register(){
//        this.usuarios = usuarios;

        nomeLabel.setForeground(Color.BLACK);
        cpfLabel.setForeground(Color.BLACK);
        nomeLabel.setBounds(100,80,40,20);
        cpfLabel.setBounds(100,180,40,20);
        nome.setBounds(100,100, 100,50);
        cpf.setBounds(100,200, 100,50);
        xLabel.setForeground(Color.BLACK);
        yLabel.setForeground(Color.BLACK);
        xLabel.setBounds(210,80,40,20);
        yLabel.setBounds(310,80,40,20);
        x.setBounds(210,100, 100,50);
        y.setBounds(310,100, 100,50);
        isAdmLabel.setForeground(Color.BLACK);
        isAdmLabel.setBounds(210,180,40,20);
        isAdmBox.setBounds(210,200,20,20);

        isAdmBox.addItemListener(e->{
//            isAdm = e.getStateChange();
            if (e.getStateChange() == 2){
                isAdm = false;
            }else {
                isAdm = true;
            }

            System.out.println(isAdm);
        });

        add(x);
        add(xLabel);
        add(y);
        add(yLabel);
        add(nomeLabel);
        add(cpfLabel);
        add(nome);
        add(cpf);
        add(isAdmLabel);
        add(isAdmBox);
        setBackground(Color.CYAN);
        setLayout(null);
        setVisible(true);

//        setSize(500,800);
    }
    public boolean admLogin(){
        if (user == null){
            return false;
        }
        System.out.println(user.getName());
        if (user != null){
            return user.getRole() == Status.ADM;
        }
        return false;
    }



}


