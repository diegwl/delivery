package screens;

import entities.Status;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class RegisterUser extends JPanel {
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
        try{
            Integer.parseInt(x.getText());
        }catch (Exception e){
            return "0";
        }
        return x.getText();
    }

    public String getYL() {
        try{
            Integer.parseInt(y.getText());
        }catch (Exception e){
            return "0";
        }
        return y.getText();
    }
    public Status getRole(){
        if (isAdm){
            return Status.ADM;
        };
        return Status.USER;
    }

    public RegisterUser(Color color){

        nomeLabel.setForeground(Color.ORANGE);
        cpfLabel.setForeground(Color.ORANGE);
        nomeLabel.setBounds(100,80,40,20);
        cpfLabel.setBounds(100,180,40,20);
        nome.setBounds(100,100, 100,50);
        cpf.setBounds(100,200, 100,50);
        xLabel.setForeground(Color.ORANGE);
        yLabel.setForeground(Color.ORANGE);
        xLabel.setBounds(210,80,40,20);
        yLabel.setBounds(310,80,40,20);
        x.setBounds(210,100, 100,50);
        y.setBounds(310,100, 100,50);
        isAdmLabel.setForeground(Color.ORANGE);
        isAdmLabel.setBounds(210,180,40,20);
        isAdmBox.setBounds(210,200,20,20);

        x.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                x.setEditable(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == KeyEvent.VK_BACK_SPACE);
                super.keyPressed(e);
            }
        });

        y.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                y.setEditable(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == KeyEvent.VK_BACK_SPACE);
                super.keyPressed(e);

            }
        });

        isAdmBox.addItemListener(e->{
            if (e.getStateChange() == 2){
                isAdm = false;
            }else {
                isAdm = true;
            }

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
        setBackground(color);
        setLayout(null);
        setVisible(true);

    }
    public boolean admLogin(){
        if (user == null){
            return false;
        }
        return user.getRole() == Status.ADM;
    }
    public void clean(){
        nomeLabel.setText("");
        cpfLabel.setText("");
        xLabel.setText("");
        yLabel.setText("");
    }



}


