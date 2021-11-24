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
        if(inflater == null){
            this.inflater = (LayoutInflater) contexto.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lista_products, null);
        }

        //preencher o item através de um ViewHolder
        ViewHolderProducts vHolder = (ViewHolderProducts) convertView.getTag();
        if(vHolder == null){
            vHolder = new ViewHolderProducts(convertView);
            convertView.setTag(vHolder);
        }

        vHolder.update(this.listaProducts.get(position));

        return convertView;
    }

    private class ViewHolderProducts {
        private TextView tvName, tvPrice, tvid_category;
        private ImageView ivCapa;

        public ViewHolderProducts(View view) {
            tvName = view.findViewById(R.id.tvName);
            tvPrice = view.findViewById(R.id.tvPrice);
            tvPrice = view.findViewById(R.id.tvPrice);
            //tvid_category = view.findViewById(R.id.tvid_category);
        }

        public void update(Products products) {
            this.tvName.setText(products.getName());
            this.tvPrice.setText(""+products.getPrice());
            //this.tvid_category.setText(""+products.getId_category());
            //ivCapa.setImageResource(livro.getCapa());

        }
    }
}
