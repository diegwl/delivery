package application;

import components.Button;
import components.ErrorPopUp;
import components.SuccessPopUp;
import configs.BasicConfigs;
import configs.CadFoods;
import configs.CadRes;
import entities.Order;
import entities.Restaurant;
import entities.Status;
import entities.User;
import screens.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class App extends JFrame {
    // LISTS
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    CadRes cadRes = new CadRes();
    CadFoods cadFoods = new CadFoods();

    // SCREENS
    Login login = new Login(users, Color.BLUE);
    Shopping shopping = new Shopping(restaurants, orders, new Color(0x8F3060));
    ShoppingCart cart = new ShoppingCart(restaurants, orders, new Color(0x71379F));
    RegisterUser registerUser = new RegisterUser(new Color(0xB71E44));
    Quietus quietus = new Quietus(new Color(0xE5B678));
    RegisterRest registerRest = new RegisterRest(restaurants, new Color(0xB61C1C));
    RegisterFood registerFood = new RegisterFood(restaurants, new Color(0x8C1D1D));

    // COMPONENTS
    BasicConfigs bc = new BasicConfigs();
    Button logar = new Button("login");
    Button voltarLogar = new Button("voltar");
    Button voltarResLogar = new Button("voltar");
    Button voltarFoodLogar = new Button("voltar");
    Button registerU = new Button("registar");
    Button registerR = new Button("cadastrar restaurante");
    Button registerF = new Button("cadastrar comida");
    Button registerUserButton = new Button("Register User");
    Button registerRestButton = new Button("Register Restaurant");
    Button registerFoodButton = new Button("Register Food");
    Button deslogar = new Button("logout");
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

        //register
        voltarLogar.setBounds(10, 10 , 100, 50);
        registerUserButton.setBounds(200, 400, 100, 50);

        //login
        logar.setBounds(250, 400, 150, 50);
        registerU.setBounds(400, 400,150,50);
        registerR.setBounds(250, 500, 150, 50);
        registerF.setBounds(400, 500, 150, 50);

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

        addBasicConfigsRestaurants();
        addBasicConfigsUsers();
        addRoutes();
        for (User user : users){
            System.out.println(user.getRole());
        }

        //REGISTER
        registerUser.add(voltarLogar);
        registerUser.add(registerUserButton);

        //LOGIN
        login.add(logar);
        login.add(registerU);
        login.add(registerR);
        login.add(registerF);

        //SHOPPING
        shopping.add(deslogar);
        shopping.add(carrinho);

        //CART
        cart.add(voltar);
        cart.add(comprar);

        //QUIETUS
        quietus.add(ok);

        //REGISTER RESTAURANT
        registerRest.add(registerRestButton);
        registerRest.add(voltarResLogar);

        //REGISTER COMIDA
        registerFood.add(registerFoodButton);
        registerFood.add(voltarFoodLogar);

        setVisible(true);
    }

    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (c == ' '){
                continue;
            }
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
    public void cadastrarRestaurante(String[] allInOne, boolean showMessage){
        cadastrarRestaurante(allInOne[0], Integer.parseInt(allInOne[1]), Integer.parseInt(allInOne[2]), showMessage);
    }
    public void cadastrarRestaurante(String[] various){
        cadastrarRestaurante(various, false);
    }

    public boolean cadastrarRestaurante(String nome, int x, int y, boolean showMessage){
        for (Restaurant rest : restaurants){
            if (Objects.equals(nome, rest.getName()) || !isAlpha(nome)){
                new ErrorPopUp("ATENTITON","It already has a restaurant with this name, or is invalid");
                return false;
            }
        }
        restaurants.add(new Restaurant(idRes, nome, x,y));
        idRes += 1;
        if (showMessage){
            bc.addRestaurant(nome, x, y);
            new SuccessPopUp("Success", "Restaurant ["+nome+"] was created!\nNow add new foods!!!");
        }
        return true;
    }
    public boolean cadastrarUsers(String nome, String CPF, int x, int y, Status status, boolean showMessage){
        for (User user : users){
            if (Objects.equals(nome, user.getName()) || !isAlpha(nome)){
                new ErrorPopUp("ATENTITON","It already has a User with this name, or is invalid");
                return false;
            }
        }
        if (registerUser.admLogin()){
            users.add(new User(idUser, nome, CPF, x,y, status));
        }else{
            users.add(new User(idUser, nome, CPF, x,y));
        }
        idUser += 1;
        if (showMessage){
            bc.addUser(nome,CPF,x,y,status);
            new SuccessPopUp("Success", "["+nome+"] you create your account");
        }
        return true;
    }
    public void cadastrarUsers(String nome, String CPF, int x, int y, boolean showMessage){
        cadastrarUsers(nome, CPF, x, y, Status.USER, showMessage);
    }


    private void addRoutes(){
        // ACTUALLY AN ACTION
        registerUserButton.addActionListener(e ->{
            if (cadastrarUsers(registerUser.getNome(), registerUser.getCpf(),Integer.parseInt(registerUser.getXL()),Integer.parseInt(registerUser.getYL()), registerUser.getRole(), true)){
                registerUser.clean();
                voltarLogar.doClick();
            }
        });
        registerRestButton.addActionListener(e ->{
            if (cadastrarRestaurante(registerRest.getNome(), registerRest.getX(), registerRest.getX(), true)){
                registerRest.clean();
                registerF.doClick();
                registerFood.getLastRestaurant();
            }
        });
        registerFoodButton.addActionListener(e ->{
            if (registerFood.getRestaurant().adicionarLanche(registerFood.getFoodName(), registerFood.getFoodPrice(), true)){
                registerFood.clean();
            }
        });
        // LOGIN -> REGISTER
        registerU.addActionListener(e -> {
            login.logar();
            login.setVisible(false);

            registerUser.setUser(login.getLoggedUser());
            registerUser.setVisible(true);
            setContentPane(registerUser);
        });
        // LOGIN -> REGISTER RESTAURANT
        registerR.addActionListener(e -> {
            if(!login.logar()){
                return;
            }
            if (login.getLoggedUser().getRole() != Status.ADM){
                return;
            }
            login.setVisible(false);

            registerRest.setVisible(true);
            setContentPane(registerRest);
        });
        // LOGIN -> REGISTER COMIDA
        registerF.addActionListener(e -> {
            if(!login.logar()){
                return;
            }
            if (login.getLoggedUser().getRole() != Status.ADM){
                return;
            }
            login.setVisible(false);
            registerRest.setVisible(false);

            registerFood.setRestaurants(getRestaurants());
            registerFood.refresh();
            registerFood.setVisible(true);
            setContentPane(registerFood);
        });
        // REGISTER RESTAURANT -> LOGIN
        voltarResLogar.addActionListener(e->{
            registerRest.setVisible(false);

            login.setVisible(true);
            setContentPane(login);
        });
        // REGISTER -> COMIDA LOGIN
        voltarFoodLogar.addActionListener(e->{
            registerFood.setVisible(false);

            login.setVisible(true);
            setContentPane(login);
        });
        // REGISTER -> LOGIN
        voltarLogar.addActionListener(e -> {
            registerUser.setVisible(false);

            login.setVisible(true);
            setContentPane(login);
        });
        // LOGIN -> SHOPPING
        logar.addActionListener(e -> {
            if(!login.logar()){
                return;
            }
            login.setVisible(false);

            shopping.setAllRequests(getPedidos());
            shopping.cleanFood();
            shopping.setAllRestaurants(getRestaurants());
            shopping.setUser(login.getLoggedUser());
            shopping.refresh();
            shopping.setVisible(true);
            setContentPane(shopping);
        });
        // SHOPPING -> LOGIN
        deslogar.addActionListener(e ->{
            shopping.setVisible(false);

            login.setVisible(true);
            setContentPane(login);
        });
        // SHOPPING -> CART
        carrinho.addActionListener(e ->{
            shopping.setVisible(false);
            shopping.setDefaultPrices();

            cart.setUser(login.getLoggedUser());
            cart.setAllRestaurants(getRestaurants());
            cart.setAllRequests(getPedidos());
            cart.updateRequests();
            cart.updatePrice();
            cart.setVisible(true);
            setContentPane(cart);
        });
        // CART -> QUIETUS
        comprar.addActionListener(e -> {
            if (cart.getModelRequests().isEmpty()){
                return;
            }
            cart.setVisible(false);

            quietus.setModelQuietus(cart.getModelRequests());
            quietus.refresh();
            quietus.setVisible(true);
            setContentPane(quietus);
        });
        // CART -> SHOPPING
        voltar.addActionListener(e ->{
            cart.setVisible(false);

            shopping.cleanFood();
            shopping.updateRequests();
            shopping.updatePrice();
            shopping.setVisible(true);
            setContentPane(shopping);
        });
        // QUIETUS -> CART
        ok.addActionListener(e->{
            quietus.setVisible(false);

            cart.finishShopping();
            cart.setVisible(true);
            setContentPane(cart);
        });

    }
    private void addAllUsers(){
        users.add(new User(idUser, "", "", 10,15, Status.ADM));
        idUser++;
        cadastrarUsers("Gustavo", "83", 50,50, false);
    }
    private void addAllRestaurants(){

        cadastrarRestaurante(cadRes.FirelinkShrimp(), false);
        cadastrarRestaurante(cadRes.SolaireSoup(), false);
        cadastrarRestaurante(cadRes.JohnGourmet(), false);
        cadastrarRestaurante(cadRes.Dadora(), false);
    }
    private void addAllFoods(){
        cadFoods.firelinkShrimp(restaurants.get(0));
        cadFoods.solaireSoup(restaurants.get(1));
        cadFoods.johnGourmet(restaurants.get(2));
        cadFoods.dadora(restaurants.get(3));
    }
    private void addBasicConfigsRestaurants(){
        ArrayList<String> restaurants = bc.getRests();
        for (String restaurant : restaurants){
            String[] rest = restaurant.split("-")[0].split(",");
            cadastrarRestaurante(rest[0], Integer.parseInt(rest[1]), Integer.parseInt(rest[2]), false);
            try {
                String[] foods = restaurant.split("-")[1].split(";");
                for (String food : foods){
                    String[] f = food.split(",");
                    Restaurant r = getRestaurant(rest[0]);
                    if (r == null){
                        break;
                    }
                    r.adicionarLanche(f[0],Float.parseFloat(f[1]), false);
                }
            }catch (ArrayIndexOutOfBoundsException e){
                break;
            }
        }
    }
    private void addBasicConfigsUsers(){
        ArrayList<String> users = bc.getUsers();
        for (String user : users){
            if (Objects.equals(user, "")){
                continue;
            }
            String[] u = user.replace(";","").split(",");
            if (Status.valueOf(u[4]) == Status.ADM){
                this.users.add(new User(idUser, u[0], u[1], Integer.parseInt(u[2]), Integer.parseInt(u[3]), Status.ADM));
                idUser++;
            }else{
                cadastrarUsers(u[0], u[1], Integer.parseInt(u[2]), Integer.parseInt(u[3]), Status.valueOf(u[4]), false);
            }
        }
    }

}
