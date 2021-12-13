package amsi.dei.estg.ipleiria.snakrestaurant.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Purchases;

public interface PurchasesListener {
    void onRefreshListaPurchases(ArrayList<Purchases> listapurchases);
}
