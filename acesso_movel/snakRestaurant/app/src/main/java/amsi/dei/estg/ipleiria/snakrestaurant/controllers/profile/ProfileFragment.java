package amsi.dei.estg.ipleiria.snakrestaurant.controllers.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.UserListener;
import amsi.dei.estg.ipleiria.snakrestaurant.models.SingletonGestor;
import amsi.dei.estg.ipleiria.snakrestaurant.models.User;

public class ProfileFragment extends Fragment implements UserListener {

    public TextView username, email, numero;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SingletonGestor.getInstance(getContext()).setUserlistener(this);
        getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    private void getUser() {
        SingletonGestor.getInstance(getContext()).getUserAPI(getContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view != null){

            username = (TextView) view.findViewById(R.id.tv_username);
            email = (TextView) view.findViewById(R.id.tv_mail);
            numero = (TextView) view.findViewById(R.id.tv_numero);
        }
    }

    @Override
    public void onUser(User user) {

        username.setText(user.getUsername());
        email.setText(user.getEmail());
        numero.setText("" + user.getNumero());
    }
}