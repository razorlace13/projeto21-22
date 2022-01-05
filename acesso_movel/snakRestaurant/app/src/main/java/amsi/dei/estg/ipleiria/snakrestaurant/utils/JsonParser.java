package amsi.dei.estg.ipleiria.snakrestaurant.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import amsi.dei.estg.ipleiria.snakrestaurant.models.Consumo;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Purchases;
import amsi.dei.estg.ipleiria.snakrestaurant.models.User;


public class JsonParser {

    public static ArrayList<Products> parserJsonProducts(JSONArray resposta){
        //Criar o ArrayList
        ArrayList<Products> listaproducts = new ArrayList<>();

        //percorrer o array de objectos

        try {
            for(int i = 0; i < resposta.length(); i++){
                JSONObject productsjson = (JSONObject) resposta.get(i);

                long id_product = productsjson.getLong("id_product");
                String name = productsjson.getString("name");
                double price = productsjson.getDouble("price");
                int id_category = productsjson.getInt("id_category");

                Products products = new Products(id_product, name, price, id_category);
                listaproducts.add(products);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaproducts;
    }

    public static ArrayList<Purchases> parserJsonPurchases(JSONArray resposta){
        //Criar o ArrayList
        ArrayList<Purchases> listapurchases = new ArrayList<>();

        //percorrer o array de objectos

        try {
            for(int i = 0; i < resposta.length(); i++){
                JSONObject purchasesjson = (JSONObject) resposta.get(i);

                long id_purchase = purchasesjson.getLong("id_purchase");
                int mesa = purchasesjson.getInt("mesa");
                String data = purchasesjson.getString("data");
                double valor = purchasesjson.getDouble("valor");
                int id_user = purchasesjson.getInt("id_user");

                Purchases purchases = new Purchases(id_purchase, valor, data, mesa,id_user);
                listapurchases.add(purchases);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listapurchases;
    }

    public static ArrayList<Consumo> parserJsonConsumo(JSONArray resposta){
        //Criar o ArrayList
        ArrayList<Consumo> listaconsumo = new ArrayList<>();

        //percorrer o array de objectos

        try {
            for(int i = 0; i < resposta.length(); i++){
                JSONObject consumojson = (JSONObject) resposta.get(i);

                long id_consumo = consumojson.getLong("id_consumo");
                int id_pedido = consumojson.getInt("id_pedido");
                String product = consumojson.getString("name");
                int quantidade = consumojson.getInt("quantidade");

                Consumo consumo = new Consumo(id_consumo, id_pedido, product, quantidade);
                listaconsumo.add(consumo);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaconsumo;
    }

    public static boolean isConnectionInternet(Context contexto){
        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    public static User parserJsonUser(JSONObject resposta){


        try {

            JSONObject myObject = new JSONObject(String.valueOf(resposta));

                int id = myObject.getInt("id");
                String username = myObject.getString("username");
                String email = myObject.getString("email");
                int numero = myObject.getInt("numero");

            return new User(id,username, email, numero);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  null;
    }
}
