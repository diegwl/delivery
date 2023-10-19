package screens;

import entities.Restaurant;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class RegisterFood extends JPanel {
    private Vector<String> restaurantsNames = new Vector<>();
    private JComboBox<String> restaurantsComboBox = new JComboBox<>(restaurantsNames);
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private Restaurant restaurant;
    private JTextField nome = new JTextField();
    private JTextField price = new JTextField();
    public void getLastRestaurant(){
        restaurantsComboBox.setSelectedIndex(restaurantsNames.size()-1);
    }
    public String getFoodName(){
        return nome.getText();
    }
    public float getFoodPrice(){
        try {
            return Float.parseFloat(price.getText());
        }catch (Exception e){
            return 0;
        }
    }
    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public RegisterFood(ArrayList<Restaurant> restaurants, Color color){
        this.restaurants = restaurants;
        Border blackline;

        blackline = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder borderName, borderPrice;

        borderName = BorderFactory.createTitledBorder(blackline,"Food Name");
        borderName.setTitleJustification(TitledBorder.CENTER);

        borderPrice = BorderFactory.createTitledBorder(blackline,"Food Price");
        borderPrice.setTitleJustification(TitledBorder.CENTER);

        nome.setBounds(250, 270, 300, 39);
        nome.setBorder(borderName);
        price.setBounds(250, 320, 300, 39);
        price.setBorder(borderPrice);

        restaurantsComboBox.setBounds(250, 220, 300, 24);
        restaurantsComboBox.addItemListener(e->{
            if (e.getStateChange() == ItemEvent.SELECTED){
                selectRestaurant((String) e.getItem());
            }
        });

        price.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                price.setEditable(
                        e.getKeyChar() >= '0' && e.getKeyChar() <= '9'
                        || e.getKeyChar() == KeyEvent.VK_BACK_SPACE
                        || e.getKeyChar() == '.'
                        );
                super.keyPressed(e);
            }
        });

        add(nome);
        add(price);
        add(restaurantsComboBox);

        setBackground(color);
        setLayout(null);
        setVisible(true);
    }
    public void refresh(){
        restaurantsNames.clear();
        for (Restaurant rest: restaurants){
            restaurantsNames.add(rest.getName());
        }
        restaurantsComboBox.setSelectedIndex(0);

    }
    private void selectRestaurant(String restaurant){
        for (Restaurant rest: restaurants){
            if (Objects.equals(rest.getName(), restaurant)){
                setRestaurant(rest);
            }
        }
    }
    public void clean(){
        nome.setText("");
        price.setText("");
    }
}
