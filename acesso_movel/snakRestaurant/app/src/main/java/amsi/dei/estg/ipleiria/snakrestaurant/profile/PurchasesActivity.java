package amsi.dei.estg.ipleiria.snakrestaurant.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.adaptadores.RecyclerPurchasesAdaptador;
import amsi.dei.estg.ipleiria.snakrestaurant.models.SingletonGestorPurchases;

public class PurchasesActivity extends AppCompatActivity {

    private RecyclerView rv_purchases;
    private RecyclerPurchasesAdaptador adaptador;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);
        rv_purchases = findViewById(R.id.rv_purchases);
        layoutManager = new LinearLayoutManager(this);
        rv_purchases.setLayoutManager(layoutManager);

        adaptador = new RecyclerPurchasesAdaptador(this, SingletonGestorPurchases.getInstance().getLivros());
        System.out.println(adaptador);
        rv_purchases.setAdapter(adaptador);
        rv_purchases.setItemAnimator(new DefaultItemAnimator());

    }
}