package amsi.dei.estg.ipleiria.snakrestaurant.models;

public class Shopping_card {

    private Integer id_shopping;
    private  long id_product_shopping;
    private String name_shopping;
    private double price_shopping;
    public int id_category_shopping;
    public int quantidade_shopping;
    public int id_user_shopping;

    //ID_PRODUCT_SHOPPING, NAME_SHOPPING, PRICE_SHOPPING, ID_CATEGORY_SHOPPING, QUANTIDADE


    public Shopping_card( long id_product_shopping, String name_shopping, double price_shopping, int id_category_shopping, int quantidade_shopping, int id_user_shopping) {

        this.id_product_shopping = id_product_shopping;
        this.name_shopping = name_shopping;
        this.price_shopping = price_shopping;
        this.id_category_shopping = id_category_shopping;
        this.quantidade_shopping = quantidade_shopping;
        this.id_user_shopping = id_user_shopping;
    }

    public Shopping_card(int id_shopping, long id_product_shopping, String name_shopping, double price_shopping, int id_category_shopping, int quantidade_shopping, int id_user_shopping) {
        this.id_shopping = id_shopping;
        this.id_product_shopping = id_product_shopping;
        this.name_shopping = name_shopping;
        this.price_shopping = price_shopping;
        this.id_category_shopping = id_category_shopping;
        this.quantidade_shopping = quantidade_shopping;
        this.id_user_shopping = id_user_shopping;
    }

    public Integer getId_shopping() {
        return id_shopping;
    }

    public void setId_shopping(Integer id_shopping) {
        this.id_shopping = id_shopping;
    }

    public  long getId_product_shopping() {
        return id_product_shopping;
    }

    public void setId_product_shopping(long id_product_shopping) {
        this.id_product_shopping = id_product_shopping;
    }

    public String getName_shopping() {
        return name_shopping;
    }

    public void setName_shopping(String name_shopping) {
        this.name_shopping = name_shopping;
    }

    public double getPrice_shopping() {
        return price_shopping;
    }

    public void setPrice_shopping(double price_shopping) {
        this.price_shopping = price_shopping;
    }

    public int getId_category_shopping() {
        return id_category_shopping;
    }

    public void setId_category_shopping(int id_category_shopping) {
        this.id_category_shopping = id_category_shopping;
    }

    public int getQuantidade_shopping() {
        return quantidade_shopping;
    }

    public void setQuantidade_shopping(int quantidade_shopping) {
        this.quantidade_shopping = quantidade_shopping;
    }
    public int getId_user_shopping() {
        return id_user_shopping;
    }

    public void setId_user_shopping(int id_user_shopping) {
        this.id_user_shopping = id_user_shopping;
    }

    @Override
    public String toString() {
        return "{" +
                "id_product_shopping=" + id_product_shopping +
                ",name_shopping=" + name_shopping +
                ",price_shopping=" + price_shopping +
                ",id_category_shopping=" + id_category_shopping +
                ",quantidade_shopping=" + quantidade_shopping +
                '}';
    }
}