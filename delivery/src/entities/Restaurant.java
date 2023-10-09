package entities;

import components.Error;
import components.Success;
import configs.BasicConfigs;

import java.util.ArrayList;
import java.util.Objects;

public class Restaurant {
    private BasicConfigs bc = new BasicConfigs();
    private String name;
    private int id;
    private int x;
    private int y;
    private int idLancheAtual = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int[] getLocation() {
        return new int[] {x,y};
    }
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private final ArrayList<Food> comidas = new ArrayList<>();

    public ArrayList<Food> getCardapio() {
        return comidas;
    }

    public Restaurant(int id, String name, int x, int y){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public boolean isAlpha(String name) {
        System.out.println(name);
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (c == ' '){
                continue;
            }
            if (c == '\''){
                continue;
            }
            if(!Character.isLetter(c)) {
//                System.out.println(c);
                return false;
            }
        }

        return true;
    }
    public boolean adicionarLanche(String nome, float preco, boolean showMessage){
//        System.out.println(nome);
        if (preco <= 0){
            new Error("ATENTION","Price is invalid!!!");
            return false;
        }
        for (Food food : getCardapio()){
            if (Objects.equals(nome, food.getNome()) || !isAlpha(nome)){
//                System.out.println(nome);
                new Error("ATENTION","It already has a Food with this name, or is invalid");
                return false;
            }
        }
        if (showMessage){
            bc.addFood(name,nome, preco);
            new Success("Success", "You Created ["+nome+"] in ["+name+"]");
        }
        comidas.add(new Food(idLancheAtual, nome, preco));
        idLancheAtual += 1;
        return true;
    }
    public Food getFood(String food){
        for(Food f : comidas){
            if (Objects.equals(food, f.getNome())){
                return f;
            }
        }
        return null;
    }
    public void removerComida(int id){
        comidas.removeIf(comida -> comida.id == id);
    }

}
