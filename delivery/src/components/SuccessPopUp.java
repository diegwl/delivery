package components;

import javax.swing.*;

public class SuccessPopUp extends JCheckBox {
    public SuccessPopUp(String title, String message){
        JOptionPane.showMessageDialog(new JFrame(), message, title,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
