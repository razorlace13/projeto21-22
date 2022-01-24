package amsi.dei.estg.ipleiria.snakrestaurant.controllers.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas.SignupFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.UserListener;
import amsi.dei.estg.ipleiria.snakrestaurant.main_application.MainMenuActivity;
import amsi.dei.estg.ipleiria.snakrestaurant.models.LoginSingleton;
import amsi.dei.estg.ipleiria.snakrestaurant.models.SingletonGestor;
import amsi.dei.estg.ipleiria.snakrestaurant.models.User;

public class ProfileFragment extends Fragment implements UserListener {

    public TextView username, email, numero;
    private FloatingActionButton bt_alterar, bt_compras, bt_reload;
    private long id;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SingletonGestor.getInstance(getContext()).setUserlistener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        username = (TextView) view.findViewById(R.id.tv_username);
        email = (TextView) view.findViewById(R.id.tv_mail);
        numero = (TextView) view.findViewById(R.id.tv_numero);
        getUser(false);
        bt_alterar = (FloatingActionButton)view.findViewById(R.id.FAB_Change);
        bt_alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(id,username.getText().toString(),email.getText().toString(),Integer.parseInt(numero.getText().toString()));
                SingletonGestor.getInstance(getActivity()).updateUserAPI(getActivity(), id, user);
            }
        });
        bt_compras = (FloatingActionButton)view.findViewById(R.id.FAB_Compras);
        bt_compras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(getContext(), DetalhePurchasesActivity.class);
                startActivity(menu);
            }
        });
        bt_reload = (FloatingActionButton)view.findViewById(R.id.FAB_Reload);
        bt_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUser(true);
            }
        });
        return view;
    }
    private void getUser(boolean resp) {
        SingletonGestor.getInstance(getContext()).getUserAPI(getContext());
    }

    @Override
    public void onUser(User user) {
        id = user.getId();
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        numero.setText("" + user.getNumero());
    }

    @Override
    public void onPutuser() {
        Toast.makeText(getContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
    }


}