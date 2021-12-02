package amsi.dei.estg.ipleiria.snakrestaurant.models;

public class User {

    private String username;
    private int email, numero;

    public User(String username, int email, int numero) {
        this.username = username;
        this.email = email;
        this.numero = numero;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
