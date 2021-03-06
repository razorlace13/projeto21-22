package amsi.dei.estg.ipleiria.snakrestaurant.adaptadores;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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

import amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections;
import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.shopping_cart.shopping_cart_Fragment;
import amsi.dei.estg.ipleiria.snakrestaurant.models.BDHelper;
import amsi.dei.estg.ipleiria.snakrestaurant.models.LoginSingleton;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Shopping_card;

public class ListaProductsAdaptador extends BaseAdapter {


    private final Context contexto;
    private LayoutInflater inflater;
    private final ArrayList<Products> listaProducts;

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

        //preencher o item atrav??s de um ViewHolder
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
                //Toast.makeText(view.getContext(), "Bot??o"+position, Toast.LENGTH_SHORT).show();

                try {

                    long id_product_shopp = listaProducts.get(position).getId_product();

                    double price = listaProducts.get(position).getPrice();

                    BDHelper bdHelper = new BDHelper(view.getContext());

                    String sql = "SELECT EXISTS (SELECT * FROM '" + BDHelper.TABELA5 + "' WHERE ID_PRODUCT_SHOPPING='" + id_product_shopp + "' LIMIT 1)";
                    Cursor cursor = bdHelper.basedados.rawQuery(sql, null);
                    cursor.moveToFirst();


                    if (cursor.getInt(0) == 1) {

                        Toast.makeText(view.getContext(), "Adicionado so carrinho de compras", Toast.LENGTH_SHORT).show();
                        String qry = "select quantidade_shopping from '" + BDHelper.TABELA5 + "' WHERE ID_PRODUCT_SHOPPING='" + id_product_shopp + "'";
                        String sql_query_preco = "select price_shopping from '" + BDHelper.TABELA5 + "' WHERE ID_PRODUCT_SHOPPING='" + id_product_shopp + "'";

                        Cursor cursor1 = bdHelper.basedados.rawQuery(qry, null);
                        cursor1.moveToFirst();
                        int quantidade = cursor1.getInt(0);

                        int quantidade1 = quantidade + 1;

                        Cursor cursor2 = bdHelper.basedados.rawQuery(sql_query_preco, null);
                        cursor2.moveToFirst();
                        double preco = cursor2.getInt(0);


                        double preco1 = preco + price;

                        String strSQL = "UPDATE shopping_cart SET quantidade_shopping = "+quantidade1+" WHERE ID_PRODUCT_SHOPPING = "+id_product_shopp;
                        String strSQL_preco = "UPDATE shopping_cart SET price_shopping = "+preco1+" WHERE ID_PRODUCT_SHOPPING = "+id_product_shopp;


                        bdHelper.basedados.execSQL(strSQL);
                        bdHelper.basedados.execSQL(strSQL_preco);

                    } else {

                        Toast.makeText(view.getContext(), "Adicionado so carrinho de compras", Toast.LENGTH_SHORT).show();
                        int id = LoginSingleton.getInstance(view.getContext()).getLogin().getId();
                        long id_product_shopping = listaProducts.get(position).getId_product();
                        String name_shopping = listaProducts.get(position).getName();
                        double price_shopping = listaProducts.get(position).getPrice();
                        int id_category_shopping = listaProducts.get(position).getId_category();
                        int quantidade_shopping = 1;
                        int id_user_shopping = id;

                        Shopping_card shopping_card = new Shopping_card(id_product_shopping, name_shopping, price_shopping, id_category_shopping, quantidade_shopping,id_user_shopping);
                        bdHelper.add_to_card(shopping_card);
                    }
                }catch (Exception e){
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        return convertView;
    }

    private class ViewHolderProducts {
        private final TextView tvName;
        private final TextView tvPrice;
        private TextView tvid_category;
        private final ImageView ivCapa;
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
            this.tvPrice.setText(""+products.getPrice()+"???");
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
