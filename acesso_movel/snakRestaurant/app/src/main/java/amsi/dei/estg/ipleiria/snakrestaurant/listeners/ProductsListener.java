package amsi.dei.estg.ipleiria.snakrestaurant.listeners;


import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;


public interface ProductsListener {
    void onRefreshListaProducts(ArrayList<Products> listaproducts);

    void onRefreshDetalhes();

}
