package application;

import components.Button;
import configs.BasicConfigs;
import entities.Order;
import entities.Restaurant;
import entities.User;
import screens.Login;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class App extends JFrame {
    // LISTS
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();

    // SCREENS
    Login login = new Login(users, Color.BLUE);

    // COMPONENTS
    BasicConfigs bc = new BasicConfigs();
    Button logar = new Button("login");
    Button voltarLogar = new Button("voltar");
    Button voltarResLogar = new Button("voltar");
    Button voltarFoodLogar = new Button("voltar");
    Button registerU = new Button("registar");
    Button registerR = new Button("registar restaurante");
    Button registerF = new Button("registar comida");
    Button registerUserButton = new Button("Register User");
    Button registerRestButton = new Button("Register Restaurant");
    Button registerFoodButton = new Button("Register Food");
    Button deslogar = new Button("deslogin");
    Button carrinho = new Button("carrinho");
    Button comprar = new Button("finalizar");
    Button voltar = new Button("Voltar");
    Button ok = new Button("OK");

    // IDS
    int idRes = 0;
    int idUser = 0;
    int idPed = 0;
    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<Order> getPedidos() {
        return orders;
    }

    public void setPedidos(ArrayList<Order> orders) {
        this.orders = orders;
    }
    private Restaurant getRestaurant(String rest){
        for (Restaurant restaurant: restaurants){
            if (Objects.equals(restaurant.getName(), rest)){
                return restaurant;
            }
        }
        return null;
    }
    public App(String title){
        super(title);
        setContentPane(login);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        voltarLogar.setBounds(10, 10 , 100, 50);
        registerUserButton.setBounds(200, 400, 100, 50);

        //login
        logar.setBounds(100, 300, 100, 50);
        registerU.setBounds(300, 300,100,50);
        registerR.setBounds(100, 400, 150, 50);
        registerF.setBounds(250, 400, 150, 50);

        //SHOPPING
        deslogar.setBounds(10,10,100,50);
        carrinho.setBounds(375,10,100,50);

        //cart
        comprar.setBounds(380, 700, 100, 50);
        voltar.setBounds(10, 10, 100, 50);

        //QUIETUS
        ok.setBounds(380, 700, 100, 50);

        //REGISTER RESTAURANT
        registerRestButton.setBounds(100, 300, 300, 50);
        voltarResLogar.setBounds(10, 10 , 100, 50);

        //REGISTER COMIDA
        registerFoodButton.setBounds(100, 300, 300, 50);
        voltarFoodLogar.setBounds(10, 10 , 100, 50);

        setVisible(true);
    }

}
