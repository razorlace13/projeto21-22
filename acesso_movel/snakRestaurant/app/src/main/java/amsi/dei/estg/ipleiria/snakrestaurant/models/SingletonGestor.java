package amsi.dei.estg.ipleiria.snakrestaurant.models;

import static android.content.Context.MODE_MULTI_PROCESS;
import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.AccessToken;
import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlAPIConsumo;
import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlAPIPostConsumo;
import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlAPIPostConsumoCompleto;
import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlAPIPostPurchases;
import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlAPIProducts;
import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlAPIPurchases;
import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlAPIUser;
import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlAPIUserPost;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.snakrestaurant.controllers.profile.ProfileFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.ConsumoListener;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.ProductsListener;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.PurchasesListener;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.UserListener;
import amsi.dei.estg.ipleiria.snakrestaurant.utils.JsonParser;

public class SingletonGestor {


    private static SingletonGestor instancia = null;
    private static RequestQueue volleyQueue = null;


    private BDHelper bd = null;
    private ArrayList<Products> listaproducts;
    private ArrayList<Purchases> listapurchases;
    private ArrayList<Consumo> listaconsumo;
    private Purchases purchases;
    private User user;


    private ProductsListener productslistener;
    private ConsumoListener consumoListener;
    private PurchasesListener purchaseslistener;
    private UserListener userlistener;

    private SingletonGestor(Context contexto) {
        this.listaproducts = new ArrayList<>();
        this.listapurchases = new ArrayList<>();
        this.user = new User(0, null, null, 0);
        this.bd = new BDHelper(contexto);
    }

    public static synchronized SingletonGestor getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestor(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    public ArrayList<Consumo> getListaconsumoBD(int id) {
        listaconsumo = bd.getAllConsumo(id);
        return listaconsumo;
    }

    public ArrayList<Products> getListaproductsBD() {
        listaproducts = bd.getAllProducts();
        return listaproducts;
    }

    public ArrayList<Purchases> getListapurchasesBD() {
        listapurchases = bd.getAllPurchases();
        return listapurchases;
    }

    public Purchases getOnepurchasesBD(int position) {
        purchases = bd.getOnePurchases(position);
        return purchases;
    }

    public void setConsumolistener(ConsumoListener consumolistener) {
        this.consumoListener = consumolistener;
    }

    public void setProductslistener(ProductsListener productslistener) {
        this.productslistener = productslistener;
    }

    public void setPurchaseslistener(PurchasesListener purchaseslistener) {
        this.purchaseslistener = purchaseslistener;
    }

    public void setUserlistener(ProfileFragment profileFragment) {
        this.userlistener = profileFragment;
    }

    public void getAllProductsAPI(final Context contexto, boolean Resp) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet", Toast.LENGTH_SHORT).show();

            if (productslistener != null) {
                productslistener.onRefreshListaProducts(getListaproductsBD());
            }
        } else {
            if (!bd.getProductsCheck() || Resp) {
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
                                if (productslistener != null) {
                                    productslistener.onRefreshListaProducts(getListaproductsBD());
                                }
                                Toast.makeText(contexto, "sem acesso api", Toast.LENGTH_SHORT).show();
                            }
                        });
                volleyQueue.add(request);
            } else {
                System.out.println("SQL");
                if (productslistener != null) {
                    productslistener.onRefreshListaProducts(getListaproductsBD());
                }
            }
        }
    }

    public void getAllConsumoAPI(final Context contexto, int id_purchase) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet", Toast.LENGTH_SHORT).show();

            if (consumoListener != null) {
                consumoListener.onRefreshListaConsumo(getListaconsumoBD(id_purchase));
            }
        } else {
            if (!bd.getConsumoCheck(id_purchase)) {
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                        UrlAPIConsumo + id_purchase + AccessToken, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                listaconsumo = JsonParser.parserJsonConsumo(response);
                                bd.adicionarConsumoBD(listaconsumo);

                                if (consumoListener != null) {
                                    consumoListener.onRefreshListaConsumo(getListaconsumoBD(id_purchase));
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if (consumoListener != null) {
                                    consumoListener.onRefreshListaConsumo(getListaconsumoBD(id_purchase));
                                }
                                Toast.makeText(contexto, "sem acesso api", Toast.LENGTH_SHORT).show();
                            }
                        });
                volleyQueue.add(request);
            } else {

                if (consumoListener != null) {
                    consumoListener.onRefreshListaConsumo(getListaconsumoBD(id_purchase));
                }
            }
        }
    }

    public void getAllPurchasesAPI(final Context contexto, Boolean Resp) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet", Toast.LENGTH_SHORT).show();

            if (purchaseslistener != null) {
                purchaseslistener.onRefreshListaPurchases(getListapurchasesBD());
            }
        } else {

            if (!bd.getPurchasesCheck() || Resp) {
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                        UrlAPIPurchases, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                listapurchases = JsonParser.parserJsonPurchases(response);
                                bd.adicionarPurchasesBD(listapurchases);

                                if (purchaseslistener != null) {
                                    purchaseslistener.onRefreshListaPurchases(getListapurchasesBD());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if (purchaseslistener != null) {
                                    purchaseslistener.onRefreshListaPurchases(getListapurchasesBD());
                                }
                                Toast.makeText(contexto, "sem acesso api", Toast.LENGTH_SHORT).show();
                            }
                        });
                volleyQueue.add(request);
            } else {
                if (purchaseslistener != null) {
                    purchaseslistener.onRefreshListaPurchases(getListapurchasesBD());
                }
            }
        }
    }

    public void getUserAPI(final Context contexto, boolean resp) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet", Toast.LENGTH_SHORT).show();
        } else {
            if (!bd.getUserProfileCheck() || resp) {
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                        UrlAPIUser,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                user = JsonParser.parserJsonUser(response);
                                bd.adicionarUserProfileBD(user);
                                if (user != null) {
                                    userlistener.onUser(user);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contexto, "sem acesso api", Toast.LENGTH_SHORT).show();
                    }
                });
                volleyQueue.add(request);
            } else {
                if (userlistener != null) {
                    userlistener.onUser(bd.getUserProfile());
                }
            }
        }
    }

    public void updateUserAPI(final Context contexto, long id, User user) {

        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.PUT,
                    UrlAPIUserPost,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            bd.adicionarUserProfileBD(user);
                            userlistener.onPutuser();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", user.getUsername());
                    params.put("email", user.getEmail());
                    params.put("numero", "" + user.getNumero());
                    return params;
                }
            };
            volleyQueue.add(request);
        }
    }


    public void PostPurchase(Context context, String str_valor, String str_data, String str_id_user, String str_mesa) {

        StringRequest request = new StringRequest(Request.Method.POST, UrlAPIPostPurchases, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                //registerListener.onValidateRegister();

                SharedPreferences sharedPref = context.getSharedPreferences("myKey",MODE_MULTI_PROCESS);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("value", response);
                editor.apply();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("valor", String.valueOf(str_valor));
                params.put("data", String.valueOf(str_data));
                params.put("id_user", String.valueOf(str_id_user));
                params.put("mesa", String.valueOf(str_mesa));

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    public void PostConsumo(Context context, String id_pedido, String id_product, String quantidade) {

        StringRequest request = new StringRequest(Request.Method.POST, UrlAPIPostConsumo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                //registerListener.onValidateRegister();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("id_pedido", String.valueOf(id_pedido));
                params.put("id_product", String.valueOf(id_product));
                params.put("quantidade", String.valueOf(quantidade));

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    public void PostConsumo2(Context context,String id_pedido, ArrayList<Shopping_card> shopping_cards) {
        ArrayList<ConsumoPost> consumoArray= new ArrayList<>();
        for (int i=0;shopping_cards.size() != i; i++){
            int id_product = (int) shopping_cards.get(i).getId_product_shopping();
            int quantidade = (int) shopping_cards.get(i).getQuantidade_shopping();
            ConsumoPost consumoPost = new ConsumoPost(id_pedido, id_product,quantidade);
            consumoArray.add(consumoPost);
        }
        StringRequest request = new StringRequest(Request.Method.POST, UrlAPIPostConsumoCompleto, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("aqui " + error.getMessage());
            }
        }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                System.out.println(consumoArray);
                params.put("array", String.valueOf(consumoArray));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }
}