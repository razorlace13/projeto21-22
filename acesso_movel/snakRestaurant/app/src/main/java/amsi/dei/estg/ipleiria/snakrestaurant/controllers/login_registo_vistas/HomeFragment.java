package amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.Products.ProductsFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.profile.ProfileFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.shopping_cart.shopping_cart_Fragment;
import amsi.dei.estg.ipleiria.snakrestaurant.main_application.MainMenuActivity;
import amsi.dei.estg.ipleiria.snakrestaurant.models.BDHelper;

public class HomeFragment extends Fragment {

    private ConstraintLayout products_layout,perfil_layout,purchases_layout,logout_layout;
    private BDHelper bd;
    BottomNavigationView bottomNavigationView;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        products_layout = view.findViewById(R.id.products_layout);
        perfil_layout = view.findViewById(R.id.perfil_layout);
        purchases_layout = view.findViewById(R.id.purchases_layout);
        logout_layout = view.findViewById(R.id.logout_layout);


        bd = new BDHelper(getContext());

        products_layout.setOnClickListener(mCorkyListener);
        perfil_layout.setOnClickListener(mCorkyListener);
        purchases_layout.setOnClickListener(mCorkyListener);
        logout_layout.setOnClickListener(mCorkyListener);

        return view;
    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId() /*to get clicked view id**/) {
                case R.id.products_layout:
                    mostraprodutos();
                    break;
                case R.id.perfil_layout:
                    mostraprofile();
                    break;
                case R.id.purchases_layout:
                    mostracarrinho();
                    break;
                case R.id.logout_layout:
                    logout();
                    break;
                default:
                    break;
            }
        }
    };


    private void mostraprodutos() {
        Activity activity = getActivity();
        bottomNavigationView = activity.findViewById(R.id.bottomBar);
        bottomNavigationView.setSelectedItemId(R.id.products);
    }

    private void mostraprofile() {
        Activity activity = getActivity();
        bottomNavigationView = activity.findViewById(R.id.bottomBar);
        bottomNavigationView.setSelectedItemId(R.id.user);
    }
    private void mostracarrinho(){
        Activity activity = getActivity();
        bottomNavigationView = activity.findViewById(R.id.bottomBar);
        bottomNavigationView.setSelectedItemId(R.id.shop);
    }

    private void logout(){
        Activity activity = getActivity();
        bottomNavigationView = activity.findViewById(R.id.bottomBar);
        bottomNavigationView.setSelectedItemId(R.id.logout);
    }

}