package amsi.dei.estg.ipleiria.snakrestaurant.controllers.shopping_cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.adaptadores.RecyclerShoppingAdapter;
import amsi.dei.estg.ipleiria.snakrestaurant.models.BDHelper;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Shopping_card;

public class shopping_cart_Fragment extends Fragment {

    RecyclerView recyclerView;

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
        adicionar();
        BDHelper bdHelper = new BDHelper(getContext());
        List<Shopping_card> shopping_card = bdHelper.getAllCard();

        if (shopping_card.size() > 0){
             RecyclerShoppingAdapter recyclerShoppingAdapter = new RecyclerShoppingAdapter(shopping_card,getContext());
            recyclerView.setAdapter(recyclerShoppingAdapter);
            //return;
        }else{
            Toast.makeText(getContext(), "sem dados ainda", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void adicionar(){

        //o adicionar ainda esta em modo estatico
        /*
        int id_shopping = 1;
        int id_product_shopping = 1;
        String name_shopping = "NAME";
        int price_shopping = 1;
        int id_category_shopping=1 ;
        int quantidade_shopping=1;

            BDHelper bdHelper = new BDHelper(getContext());
            Shopping_card shopping_card = new Shopping_card(id_product_shopping,id_shopping,  name_shopping,  price_shopping, id_category_shopping, quantidade_shopping);
            bdHelper.add_to_card(shopping_card);
            */

        }
    }
