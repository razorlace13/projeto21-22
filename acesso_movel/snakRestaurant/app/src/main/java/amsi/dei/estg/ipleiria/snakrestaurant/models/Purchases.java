package amsi.dei.estg.ipleiria.snakrestaurant.models;

import java.time.LocalDate;
import java.util.Date;

public class Purchases {
    private int id_purchase ,id_user, mesa;
    private LocalDate data;
    private double valor;

    public Purchases(int id_purchase, int id_user, int mesa, LocalDate data, double valor) {
        this.id_purchase = id_purchase;
        this.id_user = id_user;
        this.mesa = mesa;
        this.data = data;
        this.valor = valor;
    }

    public int getId_purchase() {
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
