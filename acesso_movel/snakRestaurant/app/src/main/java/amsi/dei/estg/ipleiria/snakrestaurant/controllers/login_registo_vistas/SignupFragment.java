package amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas;

import static amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections.UrlResgister;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.LoginListener;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.RegisterListener;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Login;
import amsi.dei.estg.ipleiria.snakrestaurant.models.LoginSingleton;


public class SignupFragment extends Fragment implements RegisterListener {

    EditText et_username, et_password, et_confirm_password, et_phone, et_email, et_nif;
    String str_username, str_password,str_confirm_password, str_phone, str_email, str_nif;
    private Button button_signin, button_signup;


    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginSingleton.getInstance(getContext()).setRegisterListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        et_email = view.findViewById(R.id.et_email);
        et_username = view.findViewById(R.id.et_username);
        et_phone = view.findViewById(R.id.et_phone);
        et_nif = view.findViewById(R.id.et_nif);
        et_password = view.findViewById(R.id.et_password);
        et_confirm_password = view.findViewById(R.id.et_confirm_password);
        button_signin = view.findViewById(R.id.button_signin);
        button_signup = view.findViewById(R.id.button_signup);

        button_signin.setOnClickListener(mCorkyListener);
        button_signup.setOnClickListener(mCorkyListener);

        return view;
    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_signin:

                    Fragment fragmento = new LoginFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_frame, fragmento);
                    fragmentTransaction.commit();
                    break;
                case R.id.button_signup:
                    register();
                    break;

                default:
                    break;
            }

        }
    };

    private void register() {
        str_username = this.et_username.getText().toString().trim();
        str_email = this.et_email.getText().toString().trim();
        str_phone = this.et_phone.getText().toString().trim();
        str_nif = et_nif.getText().toString().trim();
        str_password = et_password.getText().toString().trim();
        str_confirm_password = et_confirm_password.getText().toString().trim();


        if(str_username.equals("")){
            Toast.makeText(getContext(), "O username não pode ser vazio!", Toast.LENGTH_SHORT).show();
        }
        else if (str_email.equals("")){
            Toast.makeText(getContext(), "O email não pode ser vazio!", Toast.LENGTH_SHORT).show();
        }
        else if (str_phone.equals("")){
            Toast.makeText(getContext(), "O número de telemóvel não pode ser vazio!", Toast.LENGTH_SHORT).show();
        }
        else if (str_nif.equals("")){
            Toast.makeText(getContext(), "O nif não pode ser vazio!", Toast.LENGTH_SHORT).show();;
        }
        else if (str_password.equals("")){
            Toast.makeText(getContext(), "A password não pode ser vazio!!", Toast.LENGTH_SHORT).show();
        } else if (str_confirm_password.equals("")){
            Toast.makeText(getContext(), "A confirmação da password não pode ser vazio!!", Toast.LENGTH_SHORT).show();
        }
        else if (str_phone.length() < 9){
            Toast.makeText(getContext(), "Numero de telefone invalido!", Toast.LENGTH_SHORT).show();
            et_phone.setTextColor(Color.RED);
        }
        else if (str_nif.length() < 9){
            Toast.makeText(getContext(), "Nif invalido!", Toast.LENGTH_SHORT).show();
            et_nif.setTextColor(Color.RED);
        }
        else if (!str_password.equals(str_confirm_password)){
            Toast.makeText(getContext(), "Confirmação da password falhou!", Toast.LENGTH_SHORT).show();
            et_password.setTextColor(Color.RED);
            et_confirm_password.setTextColor(Color.RED);
        }
        else if (!isValidPassword(str_password) || !isValidPassword(str_confirm_password) ){
            Toast.makeText(getContext(), "password não cumpre os requesitos!", Toast.LENGTH_SHORT).show();
        }
        else{
            et_username.setTextColor(Color.GREEN);
            et_phone.setTextColor(Color.GREEN);
            et_nif.setTextColor(Color.GREEN);
            et_email.setTextColor(Color.GREEN);
            et_password.setTextColor(Color.GREEN);
            et_confirm_password.setTextColor(Color.GREEN);

            LoginSingleton.getInstance(getContext()).apiRegisto(getContext(),str_username,str_email,str_phone,str_nif,str_password);
        }
        /*Fragment fragmento = new LoginFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame, fragmento);
        fragmentTransaction.commit();*/
    }

    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})").matcher(password);
        return matcher.matches();
    }

    @Override
    public void onValidateRegister() {

        Bundle bundle = new Bundle();
        bundle.putString("username",str_username);
        bundle.putString("password",str_password);

        Toast.makeText(getContext(), R.string.Register_Message, Toast.LENGTH_SHORT).show();
        Fragment fragmento = new LoginFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        LoginFragment loginFragement = new LoginFragment();
        loginFragement.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragment_frame, fragmento);
        fragmentTransaction.commit();
    }
}