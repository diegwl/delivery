package components;

import javax.swing.*;

public class ErrorPopUp extends JCheckBox {
    public ErrorPopUp(String title, String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.ERROR_MESSAGE);
    }
}
