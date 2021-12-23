package amsi.dei.estg.ipleiria.snakrestaurant.adaptadores;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
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
        holder.name_shopping.setText(shopping_card.getName_shopping());
        holder.price_shopping.setText(Integer.toString(shopping_card.getPrice_shopping()));
        //holder.id_category_shopping.setText(Integer.toString(shopping_card.getId_category_shopping()));
        holder.quantidade_shopping.setText(Integer.toString(shopping_card.getQuantidade_shopping()));

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.delete_from_card(shopping_card.getId_shopping());
                shopping.remove(position);
                notifyDataSetChanged();

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
        ImageButton button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //id_shopping = itemView.findViewById(R.id.text_id_shopping);
            //id_product_shopping = itemView.findViewById(R.id.text_id_product);
            name_shopping = itemView.findViewById(R.id.edit_name_shopping);
            price_shopping = itemView.findViewById(R.id.edit_price_shopping);
            //id_category_shopping = itemView.findViewById(R.id.edit_id_category_shopping);
            quantidade_shopping = itemView.findViewById(R.id.edit_quantidade_shopping);
            button_delete = itemView.findViewById(R.id.button_delete);

        }
    }
}
