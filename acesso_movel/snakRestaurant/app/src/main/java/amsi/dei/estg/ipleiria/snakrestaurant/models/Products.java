package amsi.dei.estg.ipleiria.snakrestaurant.models;

public class Products {

    private long id_product ;
    private String name;
    private double price;
    public int id_category;
    private int capa;

    public Products(long id_product, String name, double price, int id_category) {
        this.id_product = id_product;
        this.name = name;
        this.price = price;
        this.id_category = id_category;
    }

    public long getId_product() {
        return id_product;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getCapa() {
        return capa;
    }

    public void setCapa(int capa) {
        this.capa = capa;
    }

}
