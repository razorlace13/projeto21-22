package amsi.dei.estg.ipleiria.snakrestaurant.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;

public class ListaConsumoAdaptador extends BaseAdapter{

    private Context contexto;
    private LayoutInflater inflater;
    private ArrayList<Products> listaProducts;

    public ListaConsumoAdaptador(Context contexto, ArrayList<Products> listapro) {
        this.contexto = contexto;
        this.listaProducts = listapro;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
