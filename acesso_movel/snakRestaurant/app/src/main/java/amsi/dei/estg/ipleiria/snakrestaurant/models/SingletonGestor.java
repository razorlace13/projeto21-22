package amsi.dei.estg.ipleiria.snakrestaurant.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.*;
import java.util.ArrayList;

public class SingletonGestor {
    private ArrayList<Purchases> purchases;
    private static SingletonGestor instancia = null;

    public static synchronized SingletonGestor getInstance(){
        if(instancia == null){
            instancia = new SingletonGestor();
        }
        return instancia;
    }

    private SingletonGestor() {
        //fazer codigo para receber os livros da api
    }



    public ArrayList<Purchases> getPurchases(){
        return purchases;
    }
}
