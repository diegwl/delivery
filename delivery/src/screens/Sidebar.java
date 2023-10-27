package screens;

import components.Text;
import entities.User;

import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {
    public Sidebar(Color color){
        setBackground(color);
        setLayout(null);
        setBounds(0, 0, 150, 800);
        setVisible(true);
    }
}
