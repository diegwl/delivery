package components;

import javax.swing.*;

public class Error extends JCheckBox {
    public Error(String title, String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.ERROR_MESSAGE);
    }
}
