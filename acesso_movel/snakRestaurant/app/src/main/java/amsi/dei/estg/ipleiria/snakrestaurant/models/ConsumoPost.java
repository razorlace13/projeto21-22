package amsi.dei.estg.ipleiria.snakrestaurant.models;

public class ConsumoPost {

    private String id_pedido;
    private  int id_product;
    public int quantidade;

    public ConsumoPost(String id_pedido, int id_product, int quantidade) {
        this.id_pedido = id_pedido;
        this.id_product = id_product;
        this.quantidade = quantidade;
    }

    public String getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id_pedido\": " + id_pedido +
                ",\"id_product\": " + id_product +
                ",\"quantidade\": " + quantidade +
                '}';
    }
}
