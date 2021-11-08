package amsi.dei.estg.ipleiria.snakrestaurant.models;

public class Products {

    private long id_product ;
    private String name;
    private String price;
    private String id_category;
    private int capa;

    public Products(long id_product, String name, String price, String id_category,int capa) {
        this.id_product = id_product;
        this.name = name;
        this.price = price;
        this.id_category = id_category;
        this.capa = capa;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public int getCapa() {
        return capa;
    }

    public void setCapa(int capa) {
        this.capa = capa;
    }

}
