package amsi.dei.estg.ipleiria.snakrestaurant.controllers.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

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
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_purchases);
        getPurchases();
        Rv_purchases = findViewById(R.id.Rv_purchases);
        lista_Consumo = findViewById(R.id.lista_consumo);
        layoutManager = new LinearLayoutManager(this);
        Rv_purchases.setLayoutManager(layoutManager);
        adaptador = new RecyclerPurchasesAdaptador(this,SingletonGestor.getInstance(this).getListapurchasesBD(), this);
        Rv_purchases.setAdapter(adaptador);
        progressBar = findViewById(R.id.Pb_purchases);
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

    }

    @Override
    public void onRefreshListaConsumo(ArrayList<Consumo> consumo) {
        if(consumo != null){
            lista_Consumo.setAdapter(new ListaConsumoAdaptador(this, consumo));
        }
    }
}