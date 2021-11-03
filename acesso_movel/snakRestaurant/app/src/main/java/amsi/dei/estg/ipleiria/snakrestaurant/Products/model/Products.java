package amsi.dei.estg.ipleiria.snakrestaurant.Products.model;

public class Products {

    private long id_product ;
    private String name;
    private String price;
    private String id_category;

    public Products(long id_product, String name, String price, String id_category) {
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
}
