package amsi.dei.estg.ipleiria.snakrestaurant.Connections;

import android.content.Context;
import android.widget.Toast;

import amsi.dei.estg.ipleiria.snakrestaurant.models.LoginSingleton;
import amsi.dei.estg.ipleiria.snakrestaurant.utils.JsonParser;

public class Connections {

    // substituir String pelo ipv4 da rede
    //10.80.226.82 do polo de Tv do tiago
        //10.80.226.92 do polo de Tv do Claudio

    public static final String UrlBASEAPI = "http://192.168.1.183:1884/v1/";

    private static Context contexto;

    public static final String AccessToken = "?access-token=";

    public static final String UrlAPIProducts = UrlBASEAPI + "products" + AccessToken;

    public static final String UrlResgister = UrlBASEAPI + "signup/post";

    public static final String UrlAPIUser = UrlBASEAPI + "user/";

    public static final String UrlAPIPurchases = UrlBASEAPI + "purchases" + "/purchasesuser/" ;

    public static final String UrlAPIUserPost = UrlBASEAPI + "user/" + "putsomefields/"  ;

    public static final String UrlAPIConsumo = UrlBASEAPI + "consumo/consumopedido/";

    public static final String UrlAPIPostConsumo = UrlBASEAPI + "consumo/post"+ AccessToken;

    public static final String UrlAPIPostConsumoCompleto = UrlBASEAPI + "consumo/consumocompleto"+ AccessToken;

    public static final String UrlAPIPostPurchases = UrlBASEAPI + "purchases/post"+ AccessToken;

}
