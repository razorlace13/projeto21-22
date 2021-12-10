package amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.LoginListener;
import amsi.dei.estg.ipleiria.snakrestaurant.main_application.MainMenuActivity;
import amsi.dei.estg.ipleiria.snakrestaurant.models.BDHelper;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Login;
import amsi.dei.estg.ipleiria.snakrestaurant.models.LoginSingleton;

public class LoginFragment extends Fragment implements LoginListener {

    private TextView et_username, et_password;
    private Button button_signin, button_signup;
    private String user, pass;
    private BDHelper bd;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginSingleton.getInstance(getContext()).setLoginListener(this);
        bd = new BDHelper(getContext());
        verificarutilizador();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        et_username = view.findViewById(R.id.et_username);
        et_password = view.findViewById(R.id.et_password);
        button_signin = view.findViewById(R.id.button_signin);
        button_signup = view.findViewById(R.id.button_signup);
        button_signin.setOnClickListener(mCorkyListener);
        button_signup.setOnClickListener(mCorkyListener);


        Bundle bundle = getArguments();
        
        if (bundle != null){
            String username = bundle.getString("username");
            String password = bundle.getString("password");
            et_username.setText(username);
            et_password.setText(password);
    }
        return view;
    }

    private boolean efetuarLogin() {
        user = et_username.getText().toString();
        pass = et_password.getText().toString();
        boolean userBool = isUserValid(user);
        boolean passBool = isPassValid(pass);

        if(userBool != true)
            Toast.makeText(getContext(), "Invalid User", Toast.LENGTH_SHORT).show();

        if (passBool != true)
            Toast.makeText(getContext(), "Invalid Password", Toast.LENGTH_SHORT).show();

        if(userBool == true && passBool == true) {
            return true;
        }
        return false;
    }


    private boolean isUserValid(String user){
        if(user.length() > 1 && user != "Username")
            return true;
        else
            return false;
    }

    private boolean isPassValid(String pass){
        if(pass == null)
            return false;

        if (pass.length() < 8)
            return false;
        else
            return true;
    }


    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_signin:
                    if(efetuarLogin()==true) {
                        LoginSingleton.getInstance(getContext()).apiLogin(getContext(), user, pass);
                    }
                    break;
                case R.id.button_signup:
                    Fragment fragmento = new SignupFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_frame, fragmento);
                    fragmentTransaction.commit();
                    break;

                default:
                    break;
            }

        }
    };

    public void verificarutilizador() {
        if(bd.getUser().size() > 0){
            Intent menu = new Intent(getContext(), MainMenuActivity.class);
            startActivity(menu);
        }
    }

    @Override
    public void onValidateLogin(Login login) {
        if (login.getToken() != null){
            bd.inserirDadosLogin(login);
            Intent menu = new Intent(getContext(), MainMenuActivity.class);
            startActivity(menu);
        }else{
            Toast.makeText(getContext(), "Username or password incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}