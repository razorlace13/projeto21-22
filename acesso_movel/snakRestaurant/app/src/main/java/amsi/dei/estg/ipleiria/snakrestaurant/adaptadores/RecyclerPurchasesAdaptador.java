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

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Purchases;

public class RecyclerPurchasesAdaptador extends RecyclerView.Adapter<RecyclerPurchasesAdaptador.ViewHolderPurchases> {

    private Context contexto;
    private ArrayList<Purchases> listaPurchases;

    public RecyclerPurchasesAdaptador(Context context, ArrayList<Purchases> lista){
        this.contexto = context;
        this.listaPurchases = lista;
    }

    @NonNull
    @Override
    public RecyclerPurchasesAdaptador.ViewHolderPurchases onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchases_cartao,parent,false);
        RecyclerPurchasesAdaptador.ViewHolderPurchases vHolder = new RecyclerPurchasesAdaptador.ViewHolderPurchases(view);
        return new ViewHolderPurchases(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerPurchasesAdaptador.ViewHolderPurchases holder, int position) {
        Purchases purchases = listaPurchases.get(position);
        holder.update(purchases);
        //perguntar
    }

    @Override
    public int getItemCount() {
        return listaPurchases.size();
    }

    public class ViewHolderPurchases extends RecyclerView.ViewHolder {
        private TextView tv_Data, tv_Mesa, tv_Valor;
        public ViewHolderPurchases(@NonNull View itemView) {
            super(itemView);
            tv_Data = itemView.findViewById(R.id.tv_data);
            tv_Mesa = itemView.findViewById(R.id.tv_mesa);
            tv_Valor = itemView.findViewById(R.id.tv_valor);
        }

        public void update(Purchases purchases){
            tv_Data.setText("" + purchases.getData());
            tv_Mesa.setText("" + purchases.getMesa());
            tv_Valor.setText("" + purchases.getValor());
        }
    }
}
