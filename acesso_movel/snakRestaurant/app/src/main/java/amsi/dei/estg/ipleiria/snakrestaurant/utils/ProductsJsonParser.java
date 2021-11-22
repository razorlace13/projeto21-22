package amsi.dei.estg.ipleiria.snakrestaurant.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;


public class ProductsJsonParser {

    public static ArrayList<Products> parserJsonProducts(JSONArray resposta){
        //Criar o ArrayList
        ArrayList<Products> listaproducts = new ArrayList<>();

        //percorrer o array de objectos

        try {
            for(int i = 0; i < resposta.length(); i++){
                JSONObject productsjson = (JSONObject) resposta.get(i);

                long id_product = productsjson.getLong("id_product");
                String name = productsjson.getString("name");
                int price = productsjson.getInt("price");
                int id_category = productsjson.getInt("id_category");

                //criar o objeto livro
                Products products = new Products(id_product, name, price, id_category);
                //adicionar o livro à lista
                listaproducts.add(products);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaproducts;
    }

    public static Products parserJsonLivro(JSONObject resposta){
        Products products = null;

        try {
            long id_product = resposta.getLong("id_product");
            String name = resposta.getString("name");
            int price = resposta.getInt("price");
            int id_category = resposta.getInt("id_category");

            products = new Products(id_product, name, price, id_category);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return products;
    }

    public static Products parserJsonLivro(String resposta){
        Products products = null;

        try {
            JSONObject productsjson = new JSONObject(resposta);

            long id_product = productsjson.getLong("id_product");
            String name = productsjson.getString("name");
            int price = productsjson.getInt("price");
            int id_category = productsjson.getInt("id_category");

            products = new Products(id_product, name, price, price);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return products;
    }

    public static String parserJsonLogin(String resposta){
        String token = null;

        try {
            JSONObject login = new JSONObject(resposta);
            if(login.getBoolean("success")){
                token = login.getString("token");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return token;
    }

    public static boolean isConnectionInternet(Context contexto){
        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
