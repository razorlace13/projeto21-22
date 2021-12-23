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
    private int id_purchase,mesa;
    private double valor;

    public Purchases(int id_purchase, int mesa, double valor) {
        this.id_purchase = id_purchase;
        this.mesa = mesa;
        this.valor = valor;
    }
    
    public Purchases() {
        
    }

    public int getId_purchase() {
        return id_purchase;
    }

    public int getMesa() {
        return mesa;
    }

    public double getValor() {
        return valor;
    }

    public void setId_purchases(int id_purchase) {
        this.id_purchase = id_purchase;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
   
    public String toString()
    {
        return "id_purchases: "+id_purchase+", mesa: "+ mesa+", valor: "+ valor;
    }
    
}
