package amsi.dei.estg.ipleiria.snakrestaurant.controllers.Products;


import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.adaptadores.ListaProductsAdaptador;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.ProductsListener;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Products;
import amsi.dei.estg.ipleiria.snakrestaurant.models.SingletonGestor;


public class ProductsFragment extends Fragment implements ProductsListener {


    Button food_btn,drinks_btn;
    public ListView list_products;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    SearchView searchView;

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
        progressBar = view.findViewById(R.id.PB_Products);
        getProducts();
        food_btn = view.findViewById(R.id.food_btn);
        food_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Escolheu comida"  , Toast.LENGTH_SHORT).show();

                ArrayList<Products> listaFiltro = new ArrayList<>();

                for(Products livro: SingletonGestor.getInstance(getContext()).getListaproductsBD()){
                    if(livro.getId_category() == 1){
                        listaFiltro.add(livro);
                    }
                }
                list_products.setAdapter(new ListaProductsAdaptador(getContext(), listaFiltro));

            }
        });
        drinks_btn = view.findViewById(R.id.drink_btn);
        drinks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Escolheu bebidas"  , Toast.LENGTH_SHORT).show();

                ArrayList<Products> listaFiltro = new ArrayList<>();

                for(Products livro: SingletonGestor.getInstance(getContext()).getListaproductsBD()){
                    if(livro.getId_category() == 2){
                        listaFiltro.add(livro);
                    }
                }
                list_products.setAdapter(new ListaProductsAdaptador(getContext(), listaFiltro));
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorScheme(R.color.md_green_500);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProducts();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Products> listaFiltro = new ArrayList<>();

                for(Products livro: SingletonGestor.getInstance(getContext()).getListaproductsBD()){
                    if(livro.getName().toLowerCase().contains(newText.toLowerCase())){
                        listaFiltro.add(livro);
                    }
                }
                list_products.setAdapter(new ListaProductsAdaptador(getContext(), listaFiltro));

                return true;
            }
        });

        return view;
    }

    private void getProducts() {
        SingletonGestor.getInstance(getContext()).setProductslistener(this);
        SingletonGestor.getInstance(getContext()).getAllProductsAPI(getContext());
    }


    @Override
    public void onRefreshListaProducts(ArrayList<Products> listaproducts) {
        progressBar.setVisibility(View.INVISIBLE);
        if(listaproducts != null){
            list_products.setAdapter(new ListaProductsAdaptador(getContext(), listaproducts));
        }
    }

}