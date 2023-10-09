package components;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String name){
        super(name);
    }

    public Button(String name, int x, int y, int width, int height){
        super(name);
        setLocation(x, y);
        setSize(width, height);
    }

    public Button(String name, int x, int y, int width, int height, Color colorBG, Color colorFG){
        super(name);
        setLocation(x, y);
        setSize(width, height);
        setBackground(colorBG);
        setForeground(colorFG);
    }
}
