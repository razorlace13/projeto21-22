package amsi.dei.estg.ipleiria.snakrestaurant.models;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.time.*;
import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.listeners.ProductsListener;
import amsi.dei.estg.ipleiria.snakrestaurant.utils.ProductsJsonParser;

 public class SingletonGestor {

    private ArrayList<Purchases> purchases;
     // substituir String pelo ipv4 da rede
     public static final String UrlBASEAPI = "http://127.0.0.1:1884/v1/";

    private static SingletonGestor instancia = null;
     private static RequestQueue volleyQueue = null;

    public static synchronized SingletonGestor getInstance(){
        if(instancia == null){
            //instancia = new SingletonGestor(contexto);
        }
        return instancia;
    }

    private SingletonGestor(Context contexto) {
        this.listaproducts = new ArrayList<>();
        this.productsbd = new ProductsBDHelper(contexto);
    }

    public ArrayList<Purchases> getPurchases(){
        return purchases;
    }


    private ProductsBDHelper productsbd = null;
    private ArrayList<Products> listaproducts;


     public static final String UrlAPIProducts = UrlBASEAPI + "products?access-token=LiqR3mIcdkg54WhAl2P2mMX7Zuhp1D-5";
     public static final String UrlAPIProducts_food = UrlBASEAPI + "products/find_id_category/1?access-token=LiqR3mIcdkg54WhAl2P2mMX7Zuhp1D-5";
     public static final String UrlAPIProducts_drink = UrlBASEAPI + "products/find_id_category/2?access-token=LiqR3mIcdkg54WhAl2P2mMX7Zuhp1D-5";

    private ProductsListener productslistener;

    public static synchronized SingletonGestor getInstance(Context contexto){
        if(instancia == null){
            instancia = new SingletonGestor(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }


    public ArrayList<Products> getListalivrosBD() {
        listaproducts = productsbd.getAllProducts();
        return listaproducts;
    }
    public void setProductslistener(ProductsListener productslistener) {
        this.productslistener = productslistener;
    }

    public void getAllProductsAPI(final Context contexto){
        if(!ProductsJsonParser.isConnectionInternet(contexto)){
            Toast.makeText(contexto, "Não tem ligação à internet", Toast.LENGTH_SHORT).show();

            if(productslistener != null){
                productslistener.onRefreshListaProducts(getListalivrosBD());
            }
        }
        else{
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                    UrlAPIProducts, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            listaproducts = ProductsJsonParser.parserJsonProducts(response);
                            //adicionarBD(listaproducts);

                            if (productslistener != null) {
                                productslistener.onRefreshListaProducts(listaproducts);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            volleyQueue.add(request);
        }
    }
}







