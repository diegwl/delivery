package screens;

import components.Button;
import entities.Food;
import entities.Order;
import entities.Restaurant;
import entities.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class ShoppingCart extends JPanel {
    private JScrollPane scrollPaneRequests = new JScrollPane();
    private final DefaultListModel<String> modelRequests = new DefaultListModel<>();
    private final JList<String> listaRequests = new JList<>(modelRequests);
    private ArrayList<Restaurant> allRestaurants;
    private ArrayList<Order> allRequests;
    private Restaurant selectedRestaurant;
    private Food selectedFood;
    private User user;
    private final JLabel userName = new JLabel();
    private final JLabel selectedFoodPrice = new JLabel();
    private final JLabel totalFoodPrice = new JLabel();
    private final DecimalFormat df = new DecimalFormat("#.##");
    private final JLabel textX = new JLabel();
    private final JLabel textY = new JLabel();
    private final TextField fieldX = new TextField();
    private final TextField fieldY = new TextField();

    public DefaultListModel<String> getModelRequests() {
        return modelRequests;
    }

    public void setUser(User user) {
        this.user = user;
        userName.setText(Objects.equals(user.getName(), "") ?"admin":user.getName());
        fieldX.setText(String.valueOf(user.getEndereco()[0]));
        fieldY.setText(String.valueOf(user.getEndereco()[1]));
        updateRequests();
    }
    public ArrayList<Order> getAllRequests() {
        return allRequests;
    }

    public void setAllRequests(ArrayList<Order> allRequests) {
        this.allRequests = allRequests;
    }

    public ArrayList<Restaurant> getAllRestaurants() {
        return allRestaurants;
    }

    public void setAllRestaurants(ArrayList<Restaurant> allRestaurants) {
        this.allRestaurants = allRestaurants;
    }

    public ShoppingCart(ArrayList<Restaurant> allRestaurants, ArrayList<Order> allRequests,Color color){
        this.allRestaurants = allRestaurants;
        this.allRequests = allRequests;

        Border blackline;

        blackline = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder borderRequests;

        borderRequests = BorderFactory.createTitledBorder(blackline,"Requests");
        borderRequests.setTitleJustification(TitledBorder.CENTER);

        textX.setForeground(Color.ORANGE);
        textY.setForeground(Color.ORANGE);
        textX.setText("X");
        textY.setText("Y");

        textX.setBounds(450, 500, 20,20);
        textY.setBounds(450, 550, 20,20);
        fieldX.setBounds(450, 520, 190, 20);
        fieldY.setBounds(450, 570, 190, 20);

        userName.setLocation(400, 10);
        userName.setSize(300,50);
        userName.setForeground(Color.BLACK);
        userName.setFont(new Font("arial",Font.BOLD,30));

        totalFoodPrice.setLocation(240,500);
        totalFoodPrice.setSize(200,100);
        totalFoodPrice.setText("<html>Total:<br>R$0,00</html>");
        totalFoodPrice.setBackground(Color.GREEN);
        totalFoodPrice.setForeground(Color.ORANGE);
        totalFoodPrice.setFont(new Font("arial",Font.BOLD,30));

        scrollPaneRequests.setSize(405,400);
        scrollPaneRequests.setLocation(240,100);
        scrollPaneRequests.setBorder(borderRequests);

        add(totalFoodPrice);
        add(selectedFoodPrice);
        add(scrollPaneRequests);

        add(listaRequests);
        add(userName);

        add(fieldX);
        add(fieldY);
        add(textX);
        add(textY);

        addAllActions();

        setBackground(color);
        setLayout(null);
        setVisible(true);

    }

    public void updatePrice(){
        float totalPrice = 0;
        for (Order p : allRequests){
            if (p.getUserOrder() == user && !p.isFinished()){
                totalPrice += p.precoTotal();
            }
        }
        totalFoodPrice.setText("<html>Total:<br>R$"+df.format(totalPrice).replace(".",",")+"</html>");
    }

    public void updateRequests(){
        modelRequests.removeAllElements();
        for (Order p : allRequests) {
            if (p.getUserOrder() == user && !p.isFinished()) {
                ArrayList<String> foods = p.getFoodsCart();
                for (String food : foods) {
                    modelRequests.addElement(p.getRestaurantOrder().getName() + " : " + food);
                }
            }
        }
        scrollPaneRequests.setViewportView(listaRequests);
    }
    public void finishShopping(){
        for (Order p : allRequests) {
            if (p.getUserOrder() == user && !p.isFinished()) {
                p.finish();
                updateRequests();
            }
        }
        selectedFoodPrice.setText("R$0,00");
        totalFoodPrice.setText("<html>Total:<br>R$0,00</html>");
    }
    public void addAllActions() {
        listaRequests.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                return;
            }

            String request = listaRequests.getSelectedValue();

            String restaurante = request.split(" : ")[0];
            String comida = request.split(" : ")[1].split(",")[0];

            for (Restaurant rest : allRestaurants) {
                if (Objects.equals(rest.getName(), restaurante)) {
                    this.selectedRestaurant = rest;
                }
            }
            for (Food food : this.selectedRestaurant.getCardapio()) {
                if (Objects.equals(food.getNome(), comida)) {
                    selectedFood = food;
                    selectedFoodPrice.setText("R$" + df.format(selectedFood.getPreco()).replace(".", ","));
                }
            }
        });
    }

}
