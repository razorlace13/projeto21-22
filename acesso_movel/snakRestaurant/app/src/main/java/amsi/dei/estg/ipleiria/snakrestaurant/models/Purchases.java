package amsi.dei.estg.ipleiria.snakrestaurant.models;

import java.time.LocalDate;
import java.util.Date;

public class Purchases {
    private int id_user, mesa;
    private String data;
    private double valor;
    private long id_purchase;

    public Purchases(long id_purchase, double valor, String data, int mesa, int id_user) {
        this.id_purchase = id_purchase;
        this.valor = valor;
        this.data = data;
        this.mesa = mesa;
        this.id_user = id_user;
    }

    public void setId_purchase(long id_purchase) {
        this.id_purchase = id_purchase;
    }

    public long getId_purchase() {
        return id_purchase;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Purchases{" +
                "id_purchase=" + id_purchase +
                ", mesa=" + mesa +
                ", data='" + data + '\'' +
                ", valor=" + valor +
                ", id_user=" + id_user +
                '}';
    }
}
