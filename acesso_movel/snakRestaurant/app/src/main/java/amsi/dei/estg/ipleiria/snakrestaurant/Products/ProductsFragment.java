package amsi.dei.estg.ipleiria.snakrestaurant.Products;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.adaptadores.ListaProductsAdaptador;

public class ProductsFragment extends Fragment {


    Button food_btn,drinks_btn;
    ListView list_products;
    private ListaProductsAdaptador adaptador;

    public ProductsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_products, container, false);

        list_products = view.findViewById(R.id.list_products);

        food_btn = view.findViewById(R.id.food_btn);
        drinks_btn = view.findViewById(R.id.drinks_btn);

        return view;
    }
}