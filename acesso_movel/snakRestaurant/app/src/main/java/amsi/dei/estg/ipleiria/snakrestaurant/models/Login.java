package amsi.dei.estg.ipleiria.snakrestaurant.models;

public class Login {
    private String username, email, authkey;
    private int id;

    public Login(int id,String authkey, String username, String email) {
        this.id = id;
        this.authkey = authkey;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return authkey;
    }

    public void setToken(String token) {
        this.authkey = token;
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

    @Override
    public String toString() {
        return "Login{" +
                "id='" + id + '\'' +
                "authkey='" + authkey + '\'' +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        '}';
    }
}
