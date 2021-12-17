package amsi.dei.estg.ipleiria.snakrestaurant.models;

public class Shopping_card {

    private int id_shopping;
    private int id_product_shopping;
    private String name_shopping;
    private int price_shopping;
    public int id_category_shopping;
    public int quantidade_shopping;

    //ID_PRODUCT_SHOPPING, NAME_SHOPPING, PRICE_SHOPPING, ID_CATEGORY_SHOPPING, QUANTIDADE

    public Shopping_card(int id_shopping,int id_product_shopping, String name_shopping, int price_shopping, int id_category_shopping, int quantidade_shopping) {
        this.id_shopping = id_shopping;
        this.id_product_shopping = id_product_shopping;
        this.name_shopping = name_shopping;
        this.price_shopping = price_shopping;
        this.id_category_shopping = id_category_shopping;
        this.quantidade_shopping = quantidade_shopping;
    }

    public int getId_shopping() {
        return id_shopping;
    }

    public void setId_shopping(int id_shopping) {
        this.id_shopping = id_shopping;
    }

    public int getId_product_shopping() {
        return id_product_shopping;
    }

    public void setId_product_shopping(int id_product_shopping) {
        this.id_product_shopping = id_product_shopping;
    }

    public String getName_shopping() {
        return name_shopping;
    }

    public void setName_shopping(String name_shopping) {
        this.name_shopping = name_shopping;
    }

    public int getPrice_shopping() {
        return price_shopping;
    }

    public void setPrice_shopping(int price_shopping) {
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
}