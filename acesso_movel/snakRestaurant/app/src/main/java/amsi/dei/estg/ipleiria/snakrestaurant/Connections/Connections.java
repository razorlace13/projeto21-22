package amsi.dei.estg.ipleiria.snakrestaurant.Connections;

import android.content.Context;
import android.widget.Toast;

import amsi.dei.estg.ipleiria.snakrestaurant.models.LoginSingleton;
import amsi.dei.estg.ipleiria.snakrestaurant.utils.JsonParser;

public class Connections {

    // substituir String pelo ipv4 da rede
    //10.80.226.82 do polo de Tv do tiago
        //10.80.226.92 do polo de Tv do Claudio
    public static final String UrlBASEAPI = "http://10.80.226.82:1884/v1/";

    private static Context contexto;
    private static String token = LoginSingleton.getInstance(contexto).getLogin().getToken();

    public static final String UrlAPIProducts = UrlBASEAPI + "products?access-token="+token;

    public static final String UrlResgister = UrlBASEAPI + "signup/post";

    public static final String UrlAPIUser = UrlBASEAPI + "user/"+ token + "/token?access-token=" + token;

}
