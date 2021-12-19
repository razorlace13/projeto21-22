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
import amsi.dei.estg.ipleiria.snakrestaurant.models.Consumo;

public class ListaConsumoAdaptador extends BaseAdapter{

    private Context contexto;
    private LayoutInflater inflater;
    private ArrayList<Consumo> listaConsumo;

    public ListaConsumoAdaptador(Context contexto, ArrayList<Consumo> listapro) {

        this.contexto = contexto;
        this.listaConsumo = listapro;

    }

    @Override
    public int getCount() {
        return this.listaConsumo.size();
    }

    @Override
    public Object getItem(int i) {
        return this.listaConsumo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.listaConsumo.get(i).getId_consumo();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null){
            this.inflater = (LayoutInflater) contexto.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }

        if(view == null){
            view = inflater.inflate(R.layout.item_lista_consumo, null);
        }

        //preencher o item através de um ViewHolder
        ViewHolderConsumo vHolder = (ViewHolderConsumo) view.getTag();
        if(vHolder == null){
            vHolder = new ViewHolderConsumo(view);
            view.setTag(vHolder);
        }

        vHolder.update(this.listaConsumo.get(i));

        return view;
    }


    public class ViewHolderConsumo {
        private TextView tv_product;

        public ViewHolderConsumo(View view) {
            tv_product = view.findViewById(R.id.tv_product);
        }

        public void update(Consumo consumo) {
            this.tv_product.setText(consumo.getProduct());
        }

    }
}
