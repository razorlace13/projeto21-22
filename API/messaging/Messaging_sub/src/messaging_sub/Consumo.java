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
public class Consumo {
    private int id_consumo,id_pedido,quantidade;
    private String name;

    public Consumo(int id_consumo, String name, int id_pedido, int quantidade) {
        this.id_consumo = id_consumo;
        this.name = name;
        this.id_pedido = id_pedido;
        this.quantidade = quantidade;
    }
    
    public Consumo() {
    }

    public int getId_consumo() {
        return id_consumo;
    }

    public void setId_consumo(int id_consumo) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name + " (" + quantidade + ")";
    }
   
    
    
}
