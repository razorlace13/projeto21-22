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

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas.SignupFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.UserListener;
import amsi.dei.estg.ipleiria.snakrestaurant.main_application.MainMenuActivity;
import amsi.dei.estg.ipleiria.snakrestaurant.models.LoginSingleton;
import amsi.dei.estg.ipleiria.snakrestaurant.models.SingletonGestor;
import amsi.dei.estg.ipleiria.snakrestaurant.models.User;

public class ProfileFragment extends Fragment implements UserListener {

    public TextView username, email, numero;
    private Button bt_alterar, bt_compras;
    private int id;

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
        username = (TextView) view.findViewById(R.id.tv_username);
        email = (TextView) view.findViewById(R.id.tv_mail);
        numero = (TextView) view.findViewById(R.id.tv_numero);
        bt_alterar = (Button)view.findViewById(R.id.bt_Alterar_p2);
        bt_alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(id,username.getText().toString(),email.getText().toString(),Integer.parseInt(numero.getText().toString()));
                SingletonGestor.getInstance(getActivity()).updateUserAPI(getActivity(), id, user);
            }
        });
        bt_compras = (Button)view.findViewById(R.id.bt_Compras_p2);
        bt_compras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(getContext(), DetalhePurchasesActivity.class);
                startActivity(menu);
            }
        });
        return view;
    }
    private void getUser() {
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