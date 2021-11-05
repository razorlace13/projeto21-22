package amsi.dei.estg.ipleiria.snakrestaurant.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.*;
import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.R;

public class SingletonGestorPurchases {
    private ArrayList<Purchases> purchases;
    private static SingletonGestorPurchases instancia = null;

    public static synchronized SingletonGestorPurchases getInstance(){
        if(instancia == null){
            instancia = new SingletonGestorPurchases();
        }
        return instancia;
    }

    private SingletonGestorPurchases() {
        //fazer codigo para receber os livros da api
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void gerarDadosDinamico() {
        LocalDate datetime = LocalDate.now();
        purchases = new ArrayList<Purchases>();
        purchases.add(new Purchases(1, 1, 1, datetime,20));
        purchases.add(new Purchases(2, 1, 2, datetime,3));
        purchases.add(new Purchases(3, 1, 3, datetime,5));
        purchases.add(new Purchases(4, 1, 6, datetime,71));
        purchases.add(new Purchases(5, 1, 1, datetime,33));
        //outra forma de adicionar
        //Livro novo = new Livro(1002, 1999,2, "teste", "ssa","ssa");
        //livros.add(novo);
    }


    public ArrayList<Purchases> getPurchases(){
        return purchases;
    }
}
