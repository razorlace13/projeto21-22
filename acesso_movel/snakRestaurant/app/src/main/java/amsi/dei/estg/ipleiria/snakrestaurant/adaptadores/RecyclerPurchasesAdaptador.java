package amsi.dei.estg.ipleiria.snakrestaurant.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Purchases;

public class RecyclerPurchasesAdaptador extends RecyclerView.Adapter<RecyclerPurchasesAdaptador.ViewHolderPurchases> {

    private Context contexto;
    private ArrayList<Purchases> listaPurchases;
    private OnItemListener onItemListener;

    public RecyclerPurchasesAdaptador(Context context, ArrayList<Purchases> lista, OnItemListener onItemListener){
        this.contexto = context;
        this.listaPurchases = lista;
        this.onItemListener = onItemListener;
    }

    @Override
    public int getItemCount() {
        return listaPurchases.size();
    }

    @NonNull
    @Override
    public RecyclerPurchasesAdaptador.ViewHolderPurchases onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchases_cartao,parent,false);
        return new ViewHolderPurchases(view,onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerPurchasesAdaptador.ViewHolderPurchases holder, int position) {
        Purchases purchases = listaPurchases.get(position);
        holder.update(purchases);
    }


    public class ViewHolderPurchases extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_Data, tv_id, tv_Valor;
        private ImageView iv_food;
        private OnItemListener onItemListener;
        public ViewHolderPurchases(View itemView,OnItemListener onItemListener) {
            super(itemView);
            tv_Data = itemView.findViewById(R.id.tv_data_cartao);
            tv_id = itemView.findViewById(R.id.tv_id_cartao);
            tv_Valor = itemView.findViewById(R.id.tv_valor_cartao);
            iv_food = itemView.findViewById(R.id.iv_food);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        public void update(Purchases purchases){
            tv_Data.setText(purchases.getData());
            tv_id.setText("" + purchases.getId_purchase());
            tv_Valor.setText("" + purchases.getValor());
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }

}
