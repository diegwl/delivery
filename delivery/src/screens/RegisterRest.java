package screens;

import entities.Restaurant;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class RegisterRest extends JPanel {
    private ArrayList<Restaurant> restaurants = new ArrayList<>();

    private JTextField nome = new JTextField();
    private JTextField x = new JTextField();
    private JTextField y = new JTextField();
    public String getNome(){
        return nome.getText();
    }
    public int getXL(){
        try{
            return Integer.parseInt(x.getText());
        }catch (Exception e){
            return 0;
        }

    }
    public int getYL(){
        try{
            return Integer.parseInt(y.getText());
        }catch (Exception e){
            return 0;
        }

    }

    public RegisterRest(ArrayList<Restaurant> restaurants, Color color){
        Border blackline;

        blackline = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder borderName, borderX, borderY;

        borderName = BorderFactory.createTitledBorder(blackline,"Restaurant Name");
        borderName.setTitleJustification(TitledBorder.CENTER);

        borderX = BorderFactory.createTitledBorder(blackline,"Restaurant X");
        borderX.setTitleJustification(TitledBorder.CENTER);

        borderY = BorderFactory.createTitledBorder(blackline,"Restaurant Y");
        borderY.setTitleJustification(TitledBorder.CENTER);


        nome.setBounds(250,220, 300,39);
        nome.setBorder(borderName);
        x.setBounds(250,320, 150,39);
        x.setBorder(borderX);
        y.setBounds(400,320, 150,39);
        y.setBorder(borderY);

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

        add(nome);
        add(x);
        add(y);

        setBackground(color);
        setLayout(null);
        setVisible(true);
    }

    public void clean(){
        nome.setText("");
        x.setText("");
        y.setText("");
    }

}
