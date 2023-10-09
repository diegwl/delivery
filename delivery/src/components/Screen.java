package components;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    public Screen(String name){
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500,800);
        setBackground(Color.GRAY);
    }
}
