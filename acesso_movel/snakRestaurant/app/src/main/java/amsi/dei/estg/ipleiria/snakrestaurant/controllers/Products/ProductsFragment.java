package amsi.dei.estg.ipleiria.snakrestaurant.controllers.Products;


import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.adaptadores.ListaProductsAdaptador;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.ProductsListener;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;
import amsi.dei.estg.ipleiria.snakrestaurant.models.SingletonGestor;


public class ProductsFragment extends Fragment implements ProductsListener {


    Button food_btn,drinks_btn;
    ListView list_products;
    private ListaProductsAdaptador adaptador;
    private ArrayList<Products> listaproducts;

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

       // food_btn = view.findViewById(R.id.food_btn);
        //drinks_btn = view.findViewById(R.id.drinks_btn);

        SingletonGestor.getInstance(getContext()).setProductslistener(this);
        SingletonGestor.getInstance(getContext()).getAllProductsAPI(getContext());

        return view;
    }

    @Override
    public void onRefreshListaProducts(ArrayList<Products> listaproducts) {
        if(listaproducts != null){
            list_products.setAdapter(new ListaProductsAdaptador(getContext(), listaproducts));
        }
    }

    @Override
    public void onRefreshDetalhes() {

    }
}