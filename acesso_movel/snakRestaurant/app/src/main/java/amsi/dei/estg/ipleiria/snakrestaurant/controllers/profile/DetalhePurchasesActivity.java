package amsi.dei.estg.ipleiria.snakrestaurant.controllers.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.adaptadores.ListaConsumoAdaptador;
import amsi.dei.estg.ipleiria.snakrestaurant.adaptadores.ListaProductsAdaptador;
import amsi.dei.estg.ipleiria.snakrestaurant.adaptadores.RecyclerPurchasesAdaptador;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.ConsumoListener;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.PurchasesListener;
import amsi.dei.estg.ipleiria.snakrestaurant.models.BDHelper;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Consumo;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Purchases;
import amsi.dei.estg.ipleiria.snakrestaurant.models.SingletonGestor;

public class DetalhePurchasesActivity extends AppCompatActivity implements PurchasesListener, RecyclerPurchasesAdaptador.OnItemListener, ConsumoListener {

    private RecyclerPurchasesAdaptador adaptador;
    private RecyclerView Rv_purchases;
    private RecyclerView.LayoutManager layoutManager;
    private ListView lista_Consumo;
    private Dialog dialog;
    private Button bt_close;
    private ImageView iv_close;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_purchases);
        getSupportActionBar().hide();
        Rv_purchases = findViewById(R.id.Rv_purchases);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_detalhes_purchases);
        lista_Consumo = dialog.findViewById(R.id.lista_consumo);
        layoutManager = new LinearLayoutManager(this);
        Rv_purchases.setLayoutManager(layoutManager);
        adaptador = new RecyclerPurchasesAdaptador(this,SingletonGestor.getInstance(this).getListapurchasesBD(), this);
        Rv_purchases.setAdapter(adaptador);
        progressBar = findViewById(R.id.Pb_purchases);
        getPurchases();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout_Purchases);
        swipeRefreshLayout.setColorScheme(R.color.md_green_500);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPurchases();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getPurchases() {
        SingletonGestor.getInstance(this).setPurchaseslistener(this);
        SingletonGestor.getInstance(this).getAllPurchasesAPI(this);
    }

    @Override
    public void onRefreshListaPurchases(ArrayList<Purchases> listapurchases) {
        progressBar.setVisibility(View.INVISIBLE);
        if(listapurchases != null){
            Rv_purchases.setAdapter(new RecyclerPurchasesAdaptador(this, listapurchases, this));
        }
    }


    @Override
    public void onItemClick(int position) {
        Purchases purchases = SingletonGestor.getInstance(this).getOnepurchasesBD(position);
        openDialog(purchases);
    }

    private void openDialog(Purchases purchases) {
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        iv_close = dialog.findViewById(R.id.iv_close);
        bt_close = dialog.findViewById(R.id.bt_close);
        SingletonGestor.getInstance(this).setConsumolistener(this);
        SingletonGestor.getInstance(this).getAllConsumoAPI(this, (int) purchases.getId_purchase());
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onRefreshListaConsumo(ArrayList<Consumo> consumo) {
        if(consumo != null){
            lista_Consumo.setAdapter(new ListaConsumoAdaptador(this, consumo));
        }
    }
}