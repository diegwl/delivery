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

public class MainMenu extends JPanel {
    private final JScrollPane scrollPaneRests = new JScrollPane();
    private final JScrollPane scrollPaneFoods = new JScrollPane();
    private final JScrollPane scrollPaneRequests = new JScrollPane();
    private final DefaultListModel<String> modelRests = new DefaultListModel<>();
    private final DefaultListModel<String> modelFoods = new DefaultListModel<>();
    private final DefaultListModel<String> modelRequests = new DefaultListModel<>();
    private final JList<String> listaRests = new JList<>(modelRests);
    private final JList<String> listaFoods = new JList<>(modelFoods);
    private final JList<String> listaRequests = new JList<>(modelRequests);
    private final Button addRequestButton = new Button("/\\",410,612,75,75);
    private final Button removeRequestButton = new Button("\\/",410,687,75,75);
    private final ArrayList<Restaurant> allRestaurants;
    private ArrayList<Order> allRequests;
    private Restaurant selectedRestaurant;
    private Food selectedFood;
    private User user;
    private JLabel userName = new JLabel();
    private JLabel selectedFoodPrice = new JLabel();
    private JLabel totalFoodPrice = new JLabel();
    private Button addToCart = new Button("add to cart", 385,500,100,50);
    private Button refresh = new Button("refresh", 0, 500, 100, 50);

    private int idPed = 0;
    private DecimalFormat df = new DecimalFormat("#.##");

    public ArrayList<Order> getAllRequests() {
        return allRequests;
    }

    public void setAllRequests(ArrayList<Order> allRequests) {
        this.allRequests = allRequests;
    }
    public void setDefaultPrices(){
        selectedFoodPrice.setText("Select a food");
        totalFoodPrice.setText("<html>Total:<br>R$0,00</html>");
        selectedRestaurant = null;
        selectedFood = null;
        listaRests.clearSelection();
        listaFoods.clearSelection();
    }

    public void setUser(User user) {
        this.user = user;
        System.out.println(user.getName());
        userName.setText(Objects.equals(user.getName(), "") ?"admin":user.getName());
        updateRequests();
    }

    public MainMenu(ArrayList<Restaurant> restaurants, ArrayList<Order> pedidos){
        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.BLACK);

        TitledBorder borderRestaurants;
        TitledBorder borderFoods;
        TitledBorder borderRequests;

        borderRestaurants = BorderFactory.createTitledBorder(blackline,"Restaurants");
        borderRestaurants.setTitleJustification(TitledBorder.CENTER);

        borderFoods = BorderFactory.createTitledBorder(blackline,"Foods");
        borderFoods.setTitleJustification(TitledBorder.CENTER);

        borderRequests = BorderFactory.createTitledBorder(blackline,"Requests");
        borderRequests.setTitleJustification(TitledBorder.CENTER);

        listaRests.setLayoutOrientation(JList.VERTICAL);
        listaFoods.setLayoutOrientation(JList.VERTICAL);
        listaRequests.setLayoutOrientation(JList.VERTICAL);

        this.allRestaurants = restaurants;
        this.allRequests = pedidos;

        add(scrollPaneRests);
        add(scrollPaneFoods);
        add(scrollPaneRequests);

        userName.setLocation(200, 0);
        userName.setSize(300,50);
        userName.setForeground(Color.RED);
        userName.setFont(new Font("arial",Font.BOLD,30));

        selectedFoodPrice.setLocation(255,450);
        selectedFoodPrice.setSize(300,40);
        selectedFoodPrice.setText("Select a food");
        selectedFoodPrice.setForeground(Color.ORANGE);
        selectedFoodPrice.setFont(new Font("arial",Font.BOLD,30));

        totalFoodPrice.setLocation(200,500);
        totalFoodPrice.setSize(200,100);
        totalFoodPrice.setText("<html>Total:<br>R$0,00</html>");
        totalFoodPrice.setForeground(Color.ORANGE);
        totalFoodPrice.setFont(new Font("arial",Font.BOLD,30));

        scrollPaneRests.setSize(230, 300);
        scrollPaneRests.setLocation(0,150);
        scrollPaneRests.setBorder(borderRestaurants);

        scrollPaneFoods.setSize(229, 300);
        scrollPaneFoods.setLocation(255, 150);
        scrollPaneFoods.setBorder(borderFoods);

        scrollPaneRequests.setSize(400,149);
        scrollPaneRequests.setLocation(0,612);
        scrollPaneRequests.setBorder(borderRequests);

        add(addRequestButton);
        add(removeRequestButton);
        add(refresh);
        add(addToCart);
        add(totalFoodPrice);
        add(selectedFoodPrice);
        add(listaRests);
        add(listaFoods);
        add(listaRequests);
        add(userName);

        addAllActions();

        setBackground(Color.BLUE);
        setLayout(null);
        setVisible(true);
    }
    public void refresh(){
        selectedFood = null;
        for (Restaurant rest: allRestaurants) {
            System.out.println("0");
            System.out.println(modelRests.contains(rest));
            if (!modelRests.contains(rest.getName())){
                modelRests.addElement(rest.getName());
            }
        }
        scrollPaneRests.setViewportView(listaRests);
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
    public void addRequest(){

        if (selectedRestaurant == null){
            return;
        }
        if (selectedFood == null){
            return;
        }
        boolean newRequest = true;

        for (Order p : allRequests) {
            if (p.getUserOrder() == user && !p.isFinished() && p.getRestaurantOrder() == selectedRestaurant) {
                p.addOrder(selectedFood);
                newRequest = false;
            }
        }

        if (newRequest){
            Order p = new Order(idPed, selectedRestaurant, user);
            p.addOrder(selectedFood);
            allRequests.add(p);
            idPed++;
        }
        updatePrice();
        updateRequests();


    }
    public void removeRequest(){
        if (selectedRestaurant == null){
            return;
        }
        if (selectedFood == null){
            return;
        }
        for(Order p : allRequests){
            if (p.getUserOrder() == user && !p.isFinished() && p.getRestaurantOrder() == selectedRestaurant){
                p.removePedido(selectedFood, false);
            }
        }

        updatePrice();
        updateRequests();
    }


    public void updateRequests(){
        System.out.println("TESTE:       "+modelRequests);
        modelRequests.removeAllElements();
        for (Order p : allRequests) {
            if (p.getUserOrder() == user && !p.isFinished()) {
                ArrayList<String> foods = p.getFoodsCart();
                for (String food : foods) {
                    modelRequests.addElement(p.getUserOrder().getName() + " : " + food);
                }
            }
        }
        scrollPaneFoods.setViewportView(listaFoods);
        scrollPaneRequests.setViewportView(listaRequests);
    }

    public void addAllActions(){
        refresh.addActionListener(e-> refresh());
        addToCart.addActionListener(e-> addRequest());

        addRequestButton.addActionListener(e -> addRequest());
        removeRequestButton.addActionListener(e -> removeRequest());

        listaRequests.addListSelectionListener(e ->{
            if (!e.getValueIsAdjusting()){
                return;
            }
            String request = listaRequests.getSelectedValue();

            System.out.println(request);
            String restaurante = request.split(" : ")[0];
            String comida = request.split(" : ")[1].split(",")[0];

            for (Restaurant rest : allRestaurants){
                if (Objects.equals(rest.getName(), restaurante)){
                    this.selectedRestaurant = rest;
                }
            }
            for (Food food: this.selectedRestaurant.getCardapio()){
                if (Objects.equals(food.getNome(), comida)){
                    selectedFood = food;
                    selectedFoodPrice.setText("R$"+ df.format(selectedFood.getPreco()).replace(".",","));
                }
            }
            System.out.println(selectedRestaurant);
            System.out.println(selectedFood);

            System.out.println(selectedRestaurant.getName());
            System.out.println(selectedFood.getNome());

        });

        listaFoods.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()){
                return;
            }
            System.out.println(listaRequests.isSelectionEmpty());
            selectedFood = null;
            selectedFoodPrice.setText("Select a food");
            for (Food food: selectedRestaurant.getCardapio()){
                if (Objects.equals(food.getNome(), listaFoods.getSelectedValue())){
                    selectedFood = food;
                    selectedFoodPrice.setText("R$"+ df.format(selectedFood.getPreco()).replace(".",","));
                }
            }
            System.out.println(selectedFood);
            System.out.println("TESTE:       "+modelRequests);
        });
        listaRests.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()){
                return;
            }
            selectedFood = null;
            modelFoods.removeAllElements();
            for (Restaurant rest : allRestaurants){
                if (Objects.equals(rest.getName(), listaRests.getSelectedValue())){
                    selectedRestaurant = rest;
                }
            }
            for (Food food : selectedRestaurant.getCardapio()){
                if (!modelFoods.contains(food.getNome())){
                    modelFoods.addElement(food.getNome());
                }
            }

        });

    }
}
