package amsi.dei.estg.ipleiria.snakrestaurant.controllers.shopping_cart;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.adaptadores.RecyclerShoppingAdapter;
import amsi.dei.estg.ipleiria.snakrestaurant.models.BDHelper;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Consumo;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Purchases;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Shopping_card;

public class shopping_cart_Fragment extends Fragment {

    public static RecyclerView recyclerView;
    TextView teste;
    SwipeRefreshLayout swipeRefreshLayout;
    Button btn_buy;

    public shopping_cart_Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart_, container, false);

        recyclerView = view.findViewById(R.id.Recycler_shopping_card);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        btn_buy = view.findViewById(R.id.Btn_buy);
        get_Shopping_Card();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorScheme(R.color.md_green_500);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                get_Shopping_Card();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PopupShopping.class);
                intent.putExtra("type", "1");
                view.getContext().startActivity(intent);
            }
        });

        return view;
        }

    public void get_Shopping_Card() {
        BDHelper bdHelper = new BDHelper(getContext());
        List<Shopping_card> shopping_card = bdHelper.getAllCard();
        if (shopping_card.size() > 0){
            RecyclerShoppingAdapter recyclerShoppingAdapter = new RecyclerShoppingAdapter(shopping_card,getContext());
            recyclerView.setAdapter(recyclerShoppingAdapter);
            //return;
        }else{
            Toast.makeText(getContext(), "Sem Produtos", Toast.LENGTH_SHORT).show();
        }
    }

}
