package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Order {
    private final Restaurant restaurantOrder;
    private final User userOrder;
    private final ArrayList<String> foodsCart = new ArrayList<>();

    public Restaurant getRestaurantOrder() {
        return restaurantOrder;
    }

    public User getUserOrder() {
        return userOrder;
    }

    public int getId() {
        return id;
    }

    private final int id;
    private boolean finalizado;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Order(int id,Restaurant restaurantOrder, User userOrder) {
        this.id = id;
        this.restaurantOrder = restaurantOrder;
        this.userOrder = userOrder;
        this.x = userOrder.getEndereco()[0];
        this.y = userOrder.getEndereco()[1];
    }

    public boolean isEmpty(){
        return foodsCart.isEmpty();
    }

    public ArrayList<String> getFoodsCart() {
        return foodsCart;
    }


}
