package amsi.dei.estg.ipleiria.snakrestaurant.adaptadores;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.models.BDHelper;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Shopping_card;

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

        vHolder.imageButton = convertView.findViewById(R.id.imageButton);
        vHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Botão"+position, Toast.LENGTH_SHORT).show();
                //$teste = Products.getId_product(position).getName;
                //long id_product_shopping = listaProducts.get(position).getId_product();
                //String name_product_shopping = listaProducts.get(position).getName();
                //int price_product_shopping = listaProducts.get(position).getPrice();
                //int id_category_product_shopping = listaProducts.get(position).getId_category();

                try {
                long id_product_shopping = listaProducts.get(position).getId_product();
                String name_shopping = listaProducts.get(position).getName();
                int price_shopping  = listaProducts.get(position).getPrice();
                int id_category_shopping= listaProducts.get(position).getId_category();
                int quantidade_shopping=1;

                    BDHelper bdHelper = new BDHelper(view.getContext());
                    Shopping_card shopping_card = new Shopping_card(id_product_shopping,  name_shopping,  price_shopping, id_category_shopping, quantidade_shopping);
                    bdHelper.add_to_card(shopping_card);

                }catch (Exception e){

                }

            }
        });


        return convertView;
    }

    private class ViewHolderProducts {
        private TextView tvName, tvPrice, tvid_category;
        private ImageView ivCapa;
        ImageButton imageButton;

        public ViewHolderProducts(View view) {
            tvName = view.findViewById(R.id.tv_product);
            tvPrice = view.findViewById(R.id.tvPrice);
            ivCapa = view.findViewById(R.id.ivCapa);
            //tvid_category = view.findViewById(R.id.tvid_category);
            //button_add_card = View.findViewById(R.id.imageButton);
        }

        public void update(Products products) {
            this.tvName.setText(products.getName());
            this.tvPrice.setText(""+products.getPrice());
            int id_category = products.getId_category();
            if (id_category == 1){
                this.ivCapa.setImageResource(R.drawable.comida);
            }else if (id_category == 2){
                this.ivCapa.setImageResource(R.drawable.copo);
            }

            //this.tvid_category.setText(""+products.getId_category());

        }
    }
}
