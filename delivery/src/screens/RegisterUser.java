package screens;

import entities.Status;
import entities.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class RegisterUser extends JPanel {
    private JTextField nome = new JTextField();
    private JTextField cpf = new JTextField();
    private JTextField x = new JTextField();
    private JTextField y = new JTextField();
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
        Border blackline;

        blackline = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder borderName, borderX, borderY, borderCpf;

        borderName = BorderFactory.createTitledBorder(blackline,"Name");
        borderName.setTitleJustification(TitledBorder.CENTER);

        borderCpf = BorderFactory.createTitledBorder(blackline,"CPF");
        borderCpf.setTitleJustification(TitledBorder.CENTER);

        borderX = BorderFactory.createTitledBorder(blackline,"X");
        borderX.setTitleJustification(TitledBorder.CENTER);

        borderY = BorderFactory.createTitledBorder(blackline,"Y");
        borderY.setTitleJustification(TitledBorder.CENTER);

        nome.setBounds(250,220, 270,39);
        nome.setBorder(borderName);

        cpf.setBounds(250,320, 100,50);
        cpf.setBorder(borderCpf);

        x.setBounds(360,320, 100,50);
        x.setBorder(borderX);

        y.setBounds(460,320, 100,50);
        y.setBorder(borderY);

        isAdmLabel.setForeground(Color.ORANGE);
        isAdmLabel.setBounds(525,215,40,20);
        isAdmBox.setBounds(525,235,20,20);

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
        add(y);
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
        nome.setText("");
        cpf.setText("");
        x.setText("");
        y.setText("");

    }



}


