package amsi.dei.estg.ipleiria.snakrestaurant.models;

public class Consumo {

    private long id_consumo;
    private int id_pedido, id_product,quantidade;
    private String product;

    public Consumo(long id_consumo,int id_pedido,String product, int quantidade ) {
        this.id_consumo = id_consumo;
        this.id_pedido = id_pedido;
        this.product = product;
        this.quantidade = quantidade;
    }

    public long getId_consumo() {
        return id_consumo;
    }

    public void setId_consumo(long id_consumo) {
        this.id_consumo = id_consumo;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Consumo{" +
                "product='" + product + '\'' +
                '}';
    }
}
