package amsi.dei.estg.ipleiria.snakrestaurant.controllers.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.models.SingletonGestor;
import amsi.dei.estg.ipleiria.snakrestaurant.models.User;

public class ProfileFragment extends Fragment {

    public TextView username, email, numero;
    User user;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        username = view.findViewById(R.id.tv_username);
        email = view.findViewById(R.id.tv_mail);
        numero = view.findViewById(R.id.tv_numero);
        getUser();
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        numero.setText(user.getNumero());
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    private void getUser() {
        SingletonGestor.getInstance(getContext()).getUserAPI(getContext());
    }
}