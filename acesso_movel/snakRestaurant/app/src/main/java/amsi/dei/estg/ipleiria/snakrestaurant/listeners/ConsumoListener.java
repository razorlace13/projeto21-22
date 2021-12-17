package amsi.dei.estg.ipleiria.snakrestaurant.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.models.Consumo;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;

public interface ConsumoListener {
    void onRefreshListaConsumo(ArrayList<Consumo> consumo);
}
