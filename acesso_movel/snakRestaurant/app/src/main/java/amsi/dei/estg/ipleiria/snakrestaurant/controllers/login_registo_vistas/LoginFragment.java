package amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.Products.ProductsFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.profile.DetalhePurchasesActivity;
import amsi.dei.estg.ipleiria.snakrestaurant.main_application.MainMenuActivity;
import amsi.dei.estg.ipleiria.snakrestaurant.models.LoginSingleton;

public class LoginFragment extends Fragment {

    private TextView et_username, et_password;
    private Button button_signin, button_signup;
    private String user, pass;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                        LoginSingleton.getInstance(getContext(),user, pass);
                        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                            String message = "Todos os campos devem ser preenchidos";
                            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                        } else {
                            if (LoginSingleton.getInstance(getContext(), user, pass).getLogin() != null) {
                                if (LoginSingleton.getInstance(getContext(), user, pass).getLogin().isEntrar() == true) {
                                    Intent intent = new Intent(getContext(), MainMenuActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getContext(), "Conta não existente", Toast.LENGTH_SHORT).show();
                                    LoginSingleton.setInstancia(null);
                                }
                            }
                        }
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



}