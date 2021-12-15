package amsi.dei.estg.ipleiria.snakrestaurant.controllers.shopping_cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amsi.dei.estg.ipleiria.snakrestaurant.R;

public class shopping_cart_Fragment extends Fragment {



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

        return view;
    }
}