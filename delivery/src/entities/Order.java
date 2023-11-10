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

    public Order(int id, Restaurant restaurantOrder, User userOrder) {
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

    public void addOrder(Food food){

        int index = 0;
        int qtd = 1;
        for (String f : foodsCart){
            String[] updateRequest = f.split(",");
            System.out.println("F: "+f);
            System.out.println("Food: "+food.getNome());
            if (Objects.equals(updateRequest[0], food.getNome())){
                foodsCart.remove(f);
                qtd += Integer.parseInt(updateRequest[1]);
                break;
            }
            index++;
        }
        foodsCart.add(index,food.getNome()+","+qtd+","+food.getPreco());
        System.out.println(Arrays.toString(foodsCart.toArray()));
        System.out.println(this);
    }
    public boolean isFinished(){
        return finalizado;
    }
    public void finish(){
        finalizado = true;
    }

    public void removePedido(Food food, boolean keepWish){
        int index = 0;
        int qtd = 1;
        int newQtd = 0;
        for (String f : foodsCart){
            String[] updateRequest = f.split(",");
            System.out.println("F: "+f);
            System.out.println("Food: "+food.getNome());
            if (Objects.equals(updateRequest[0], food.getNome())){
                foodsCart.remove(f);
                newQtd = Integer.parseInt(updateRequest[1])- qtd;
                break;
            }
            index++;
        }
        if (newQtd <=0){
            if (keepWish){
                newQtd = 0;
            }else{
                return;
            }
        }
        foodsCart.add(index,food.getNome()+","+newQtd+","+food.getPreco());
        System.out.println(Arrays.toString(foodsCart.toArray()));
        System.out.println(this);
    }

    @Override
    public String toString() {

        return "Pedido{" +
                "id=" + id +
                ", restaurantPedido=" + restaurantOrder.getName() +
                ", usuarioPedido=" + userOrder.getName() +
                ", foods=" + Arrays.toString(foodsCart.toArray()).replaceAll(" ", "") +
                ", finalizado=" + finalizado +
//                ", quantity=" + Arrays.toString(foodsCart.toArray()) +
                '}';
    }

    public float precoTotal(){
        float preco = 0;
        for (String f : foodsCart) {
            String[] food = f.split(",");
            preco += Integer.parseInt(food[1])*Float.parseFloat(food[2]);
        }
        return preco;
    }

}
