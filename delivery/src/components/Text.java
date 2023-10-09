package components;

import javax.swing.*;
import java.awt.*;

public class Text extends JLabel {
    public Text(String text, int x, int y, int w, int h){
        super(text);
        setFont(new Font("Arial",Font.BOLD,20));
        setLocation(x,y);
        setSize(w,h);
    }
    public Text(String text, int x, int y, int w, int h, int font, int size){
        super(text);
        setFont(new Font("Arial",font,size));
        setLocation(x,y);
        setSize(w,h);
    }
    public Text(String text, int x, int y, int w, int h, Color color){
        super(text);
        setFont(new Font("Arial",Font.BOLD,20));
        setForeground(color);
        setLocation(x,y);
        setSize(w,h);
    }
    public Text(String text, int x, int y, int w, int h, Color color, int font, int size){
        super(text);
        setFont(new Font("Arial",font,size));
        setForeground(color);
        setLocation(x,y);
        setSize(w,h);
    }
}
