package amsi.dei.estg.ipleiria.snakrestaurant.models;

public class User {

    private String username,email;
    private int numero;



    public User(String username, String email, int numero) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", numero=" + numero +
                '}';
    }
}
