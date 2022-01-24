package amsi.dei.estg.ipleiria.snakrestaurant.models;


import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlBASEAPI;
import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlResgister;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas.LoginFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas.MainActivity;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.LoginListener;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.RegisterListener;
import amsi.dei.estg.ipleiria.snakrestaurant.utils.JsonParser;
import amsi.dei.estg.ipleiria.snakrestaurant.utils.Json_Objects_Convertor;

public class LoginSingleton {
    Login login;
    private BDHelper database;
    private static LoginSingleton instancia = null;

    // volley
    private static RequestQueue volleyQueue = null;

    // listener
    private LoginListener loginListener = null;
    private RegisterListener registerListener = null;

    public static synchronized LoginSingleton getInstance(Context context) {
        if (instancia == null) {
            instancia = new LoginSingleton(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instancia;
    }

    public LoginSingleton(Context context) {
        /** Inicializar variaveis **/
        database = new BDHelper(context);
        if (database.getUser().size() > 0) {
            login = database.getUser().getFirst();
        }
    }

    public void apiLogin(Context context, final String user, final String pass) {
        /** Verificar se database existe **/
        if (database.getUser().size() > 0) {
            login = database.getUser().get(0);
        } else {
            /** Verificar se existe internet **/
            if (!JsonParser.isConnectionInternet(context)) {
                Toast.makeText(context, "No internet", Toast.LENGTH_SHORT).show();
            } else {
                final String URL_LOGIN = UrlBASEAPI + "login/get?username=" + user + "&password=" + pass;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET,
                                URL_LOGIN,
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        login = (Login) Json_Objects_Convertor.objectjsonConvert(response, Login.class);
                                        loginListener.onValidateLogin(login);
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

                volleyQueue.add(jsonObjectRequest);
            }
        }
    }
    public void apiRegisto(Context context ,final String str_username,final String str_email,final String str_phone,final String str_nif,final String str_password) {

    StringRequest request = new StringRequest(Request.Method.POST, UrlResgister, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            //Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
            registerListener.onValidateRegister();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(context, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
    ) {
        @Nullable
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            Map<String, String> params = new HashMap<String, String>();
            params.put("username", str_username);
            params.put("email", str_email);
            params.put("numero", str_phone);
            params.put("nif", str_nif);
            params.put("password_hash", str_password);
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(request);
}

    public Login getLogin() {
        return login;
    }

    public void setLoginListener(LoginFragment loginFragment){
        this.loginListener = loginFragment;
    }


    public void setRegisterListener(RegisterListener registerListener) {
        this.registerListener = registerListener;
    }
}
