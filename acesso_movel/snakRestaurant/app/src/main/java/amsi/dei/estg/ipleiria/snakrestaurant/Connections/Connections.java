package amsi.dei.estg.ipleiria.snakrestaurant.Connections;

public class Connections {

    // substituir String pelo ipv4 da rede
    //10.80.226.82 do polo de Tv do tiago
        //10.80.226.92 do polo de Tv do Claudio
    public static final String UrlBASEAPI = "http://10.80.226.82:1884/v1/";

    public static final String UrlAPIProducts = UrlBASEAPI + "products?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi";

    public static final String UrlResgister = UrlBASEAPI + "?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi";


}
