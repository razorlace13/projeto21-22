package amsi.dei.estg.ipleiria.snakrestaurant.models;

import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlAPIProducts;
import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlBASEAPI;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.listeners.ProductsListener;
import amsi.dei.estg.ipleiria.snakrestaurant.utils.JsonParser;

 public class SingletonGestor {

    private ArrayList<Purchases> purchases;

    private static SingletonGestor instancia = null;
     private static RequestQueue volleyQueue = null;

    private SingletonGestor(Context contexto) {
        this.listaproducts = new ArrayList<>();
        this.bd = new BDHelper(contexto);
    }

    public ArrayList<Purchases> getPurchases(){
        return purchases;
    }


    private BDHelper bd = null;
    private ArrayList<Products> listaproducts;
    private User user;


    public static final String UrlAPIUser = UrlBASEAPI + "user/XBl8WxAMXzp4ftkZSsN55OfJsEEAf2LA/token?access-token=XBl8WxAMXzp4ftkZSsN55OfJsEEAf2LA";


    private ProductsListener productslistener;

    public static synchronized SingletonGestor getInstance(Context contexto){
        if(instancia == null){
            instancia = new SingletonGestor(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }


    public ArrayList<Products> getListaproductsBD() {
        listaproducts = bd.getAllProducts();
        System.out.println(listaproducts);
        return listaproducts;
    }

    public void setProductslistener(ProductsListener productslistener) {
        this.productslistener = productslistener;
    }

    public void getAllProductsAPI(final Context contexto){
        if(!JsonParser.isConnectionInternet(contexto)){
            Toast.makeText(contexto, "Não tem ligação à internet", Toast.LENGTH_SHORT).show();

            if(productslistener != null){
                productslistener.onRefreshListaProducts(getListaproductsBD());
            }
        }
        else{
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                    UrlAPIProducts, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            listaproducts = JsonParser.parserJsonProducts(response);

                            bd.adicionarProductsBD(listaproducts);

                            if (productslistener != null) {
                                productslistener.onRefreshListaProducts(listaproducts);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(productslistener != null){
                                productslistener.onRefreshListaProducts(getListaproductsBD());
                            }
                            Toast.makeText(contexto,"sem acesso api", Toast.LENGTH_SHORT).show();
                        }
                    });
            volleyQueue.add(request);
        }
    }
    public void getUserAPI(final Context contexto){
         if(!JsonParser.isConnectionInternet(contexto)){
             Toast.makeText(contexto, "Não tem ligação à internet", Toast.LENGTH_SHORT).show();
         }
         else{

             JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, UrlAPIUser,
                     null, new Response.Listener<JSONObject>() {
                 @Override
                 public void onResponse(JSONObject response) {
                     user = JsonParser.parserJsonUser(response);
                 }
             }, new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                     Toast.makeText(contexto,"sem acesso api", Toast.LENGTH_SHORT).show();
                 }
             });
             volleyQueue.add(request);
         }
     }
}
