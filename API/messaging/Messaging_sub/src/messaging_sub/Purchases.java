/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messaging_sub;

/**
 *
 * @author claud
 */
public class Purchases {
    private int id_purchases,mesa;
    private double valor;

    public Purchases(int id_purchases, int mesa, double valor) {
        this.id_purchases = id_purchases;
        this.mesa = mesa;
        this.valor = valor;
    }

    public int getId_purchases() {
        return id_purchases;
    }

    public int getMesa() {
        return mesa;
    }

    public double getValor() {
        return valor;
    }

    public void setId_purchases(int id_purchases) {
        this.id_purchases = id_purchases;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
   
    
}
