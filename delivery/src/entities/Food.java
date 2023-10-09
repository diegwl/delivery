package entities;

public class Food {
    int id;
    String nome;
    float preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public Food(int id, String nome, float preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
}
