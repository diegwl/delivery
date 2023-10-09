package components;

import javax.swing.*;

public class Success extends JCheckBox {
    public Success(String title, String message){
        JOptionPane.showMessageDialog(new JFrame(), message, title,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
