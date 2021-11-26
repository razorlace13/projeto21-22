package amsi.dei.estg.ipleiria.snakrestaurant.models;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.listeners.ProductsListener;
import amsi.dei.estg.ipleiria.snakrestaurant.utils.ProductsJsonParser;

 public class SingletonGestor {

    private ArrayList<Purchases> purchases;
     // substituir String pelo ipv4 da rede
     //10.80.226.82 do polo de Tv do tiago
     //10.80.226.92 do polo de Tv do Claudio
     public static final String UrlBASEAPI = "http://192.168.1.153:1884/v1/";

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
        this.productsbd = new BDHelper(contexto);
    }

    public ArrayList<Purchases> getPurchases(){
        return purchases;
    }


    private BDHelper productsbd = null;
    private ArrayList<Products> listaproducts;


    public static final String UrlAPIProducts = UrlBASEAPI + "products?access-token=XBl8WxAMXzp4ftkZSsN55OfJsEEAf2LA";

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
        System.out.println(listaproducts);
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

                            productsbd.adicionarProductsBD(listaproducts);

                            if (productslistener != null) {
                                productslistener.onRefreshListaProducts(listaproducts);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(productslistener != null){
                                productslistener.onRefreshListaProducts(getListalivrosBD());
                            }
                            Toast.makeText(contexto,"sem acesso api", Toast.LENGTH_SHORT).show();
                        }
                    });
            volleyQueue.add(request);
        }
    }
}







