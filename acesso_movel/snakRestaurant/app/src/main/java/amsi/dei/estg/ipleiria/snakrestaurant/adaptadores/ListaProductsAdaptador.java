package amsi.dei.estg.ipleiria.snakrestaurant.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;

public class ListaProductsAdaptador extends BaseAdapter {

    private Context contexto;
    private LayoutInflater inflater;
    private ArrayList<Products> listaProducts;

    public ListaProductsAdaptador(Context contexto, ArrayList<Products> lista){
        this.contexto = contexto;
        this.listaProducts = lista;
    }

    @Override
    public int getCount() {
        return this.listaProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.listaProducts.get(position).getId_product();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parents) {
        return null;
    }

    private class ViewHolderProducts {
        private TextView tvName, tvPrice;
        private ImageView ivCapa;

        public ViewHolderProducts(View view) {
            tvName = view.findViewById(R.id.tvName);
            tvPrice = view.findViewById(R.id.tvPrice);
            ivCapa = view.findViewById(R.id.ivCapa);
        }

        public void update(Products products) {
            this.tvName.setText(products.getName());
            this.tvPrice.setText(products.getPrice());
            //ivCapa.setImageResource(livro.getCapa());

        }
    }
}
