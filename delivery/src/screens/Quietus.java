package screens;

import entities.Order;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class Quietus extends JPanel {
    private final DecimalFormat df = new DecimalFormat("#.##");
    private JScrollPane scrollPaneQuietus = new JScrollPane();
    private DefaultListModel<String> modelQuietus = new DefaultListModel<>();
    private DefaultListModel<String> modelQuietusPlaceholder = new DefaultListModel<>();
    private final JList<String> listaQuietus = new JList<>(modelQuietus);

    public void setModelQuietus(DefaultListModel<String> modelQuietus) {
        this.modelQuietus = modelQuietus;
        scrollPaneQuietus.setViewportView(listaQuietus);
    }

    public DefaultListModel<String> getModelQuietus() {
        return modelQuietus;
    }

    public Quietus(Color color){
        Border blackline;

        blackline = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder borderQuietus;

        borderQuietus = BorderFactory.createTitledBorder(blackline,"Quietus");
        borderQuietus.setTitleJustification(TitledBorder.CENTER);

        scrollPaneQuietus.setSize(405,400);
        scrollPaneQuietus.setLocation(240,80);
        scrollPaneQuietus.setBorder(borderQuietus);

        add(scrollPaneQuietus);

        setBackground(color);
        setLayout(null);
        setVisible(true);
    }
    public void refresh(){
        String lastName = modelQuietus.get(0).split(" : ")[0];
        double subTotal = 0;
        double total = 0;
        modelQuietusPlaceholder.removeAllElements();
        modelQuietusPlaceholder.addElement(lastName+":");
        for (int i = 0; i < modelQuietus.getSize(); i++){
            String currentName = modelQuietus.get(i).split(" : ")[0];
            String foodQtdPrice = modelQuietus.get(i).split(" : ")[1];

            int foodQtd = Integer.parseInt(foodQtdPrice.split(",")[1]);
            float foodPrice = Float.parseFloat(foodQtdPrice.split(",")[2]);
            if (foodQtd == 0){
                continue;
            }
            if (!Objects.equals(currentName, lastName)){
                lastName = currentName;
                modelQuietusPlaceholder.addElement("subtotal: R$"+ df.format(subTotal));
                subTotal = 0;
                modelQuietusPlaceholder.addElement("------------------------------");
                modelQuietusPlaceholder.addElement(currentName+":");
            }
            subTotal += foodQtd * foodPrice;
            total += foodQtd * foodPrice;
            modelQuietusPlaceholder.addElement(" -"+foodQtdPrice.replace(",", " - "));
        }
        modelQuietusPlaceholder.addElement("subtotal: R$"+ df.format(subTotal));
        modelQuietusPlaceholder.addElement("------------------------------");
        modelQuietusPlaceholder.addElement("Total: R$"+df.format(total));
        listaQuietus.setModel(modelQuietusPlaceholder);
        scrollPaneQuietus.setViewportView(listaQuietus);
    }

}
