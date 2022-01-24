package amsi.dei.estg.ipleiria.snakrestaurant.adaptadores;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.shopping_cart.PopupShopping;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.shopping_cart.shopping_cart_Fragment;
import amsi.dei.estg.ipleiria.snakrestaurant.models.BDHelper;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Shopping_card;


public class RecyclerShoppingAdapter extends RecyclerView.Adapter<RecyclerShoppingAdapter.ViewHolder>{


    List<Shopping_card> shopping;
    Context context;
    BDHelper databaseHelper;

    public RecyclerShoppingAdapter(List<Shopping_card> shopping, Context context) {
        this.shopping = shopping;
        this.context = context;
        databaseHelper = new BDHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        final Shopping_card shopping_card = shopping.get(position);

        //holder.id_shopping.setText(Integer.toString(shopping_card.getId_shopping()));
        //holder.id_product_shopping.setText(Long.toString(shopping_card.getId_product_shopping()));
        // holder.id_category_shopping.setText(Integer.toString(shopping_card.getId_category_shopping()));

        holder.name_shopping.setText(shopping_card.getName_shopping());
        holder.price_shopping.setText(Double.toString(shopping_card.getPrice_shopping()));
        holder.quantidade_shopping.setText(Integer.toString(shopping_card.getQuantidade_shopping()));

        holder.post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price = String.valueOf(shopping.get(position).getPrice_shopping());
                String quantidade = String.valueOf(shopping.get(position).getQuantidade_shopping());
                String id_product = String.valueOf(shopping.get(position).getId_product_shopping());
               // context.startActivity(new Intent(view.getContext(), PopupShopping.class));

                Intent intent = new Intent(view.getContext(), PopupShopping.class);
                intent.putExtra("type", "0");
                intent.putExtra("price", price);
                intent.putExtra("quantidade", quantidade);
                intent.putExtra("id_product", id_product);
                view.getContext().startActivity(intent);

            }
        });

        holder.add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BDHelper bdHelper = new BDHelper(view.getContext());

                long id_product_shopp = shopping.get(position).getId_product_shopping();

                int quantidade = shopping.get(position).getQuantidade_shopping();

                double price = shopping.get(position).getPrice_shopping();

                double preco_original = bdHelper.preco_original(id_product_shopp);

                int quantidade1 = quantidade + 1;

                double preco1 = price + preco_original;

                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                preco1 = Double.valueOf(decimalFormat.format(preco1));

                bdHelper.adicionar_ao_carrinho(quantidade1,preco1,id_product_shopp);
                notifyDataSetChanged();

                List<Shopping_card> shopping_card = bdHelper.getAllCard(view.getContext());

                if (shopping_card.size() > 0){
                    RecyclerShoppingAdapter recyclerShoppingAdapter = new RecyclerShoppingAdapter(shopping_card, view.getContext());
                    shopping_cart_Fragment.recyclerView.setAdapter(recyclerShoppingAdapter);
                    //return;
                }else{
                    Toast.makeText(view.getContext(), "sem dados ainda", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long id_product_shopp = shopping.get(position).getId_product_shopping();

                int quantidade = shopping.get(position).getQuantidade_shopping();

                double price = shopping.get(position).getPrice_shopping();

                BDHelper bdHelper = new BDHelper(view.getContext());

                double preco_original = bdHelper.preco_original(id_product_shopp);

                int quantidade1 = quantidade - 1;
                double preco1 = price - preco_original;

                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                preco1 = Double.valueOf(decimalFormat.format(preco1));

                if (quantidade1 == 0){
                    databaseHelper.delete_from_card(shopping_card.getId_shopping());
                    shopping.remove(position);
                    notifyDataSetChanged();
                }else {

                    bdHelper.remover_ao_carrinho(quantidade1,preco1,id_product_shopp);
                    notifyDataSetChanged();

                    List<Shopping_card> shopping_card = bdHelper.getAllCard(view.getContext());

                    if (shopping_card.size() > 0){
                        RecyclerShoppingAdapter recyclerShoppingAdapter = new RecyclerShoppingAdapter(shopping_card, view.getContext());
                        shopping_cart_Fragment.recyclerView.setAdapter(recyclerShoppingAdapter);
                        //return;
                    }else{
                        Toast.makeText(view.getContext(), "sem dados ainda", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopping.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id_shopping;
        TextView id_product_shopping;
        TextView name_shopping;
        TextView price_shopping;
        TextView id_category_shopping;
        TextView quantidade_shopping;
        Button post_btn;
        ImageButton add_btn;
        ImageButton remove_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //id_shopping = itemView.findViewById(R.id.text_id_shopping);
            //id_product_shopping = itemView.findViewById(R.id.text_id_product);
            name_shopping = itemView.findViewById(R.id.edit_name_shopping);
            price_shopping = itemView.findViewById(R.id.edit_price_shopping);
            //id_category_shopping = itemView.findViewById(R.id.edit_id_category_shopping);
            quantidade_shopping = itemView.findViewById(R.id.edit_quantidade_shopping);
            post_btn = itemView.findViewById(R.id.post_btn);
            add_btn = itemView.findViewById(R.id.add_btn);
            remove_btn = itemView.findViewById(R.id.remove_btn);
        }
    }
}
