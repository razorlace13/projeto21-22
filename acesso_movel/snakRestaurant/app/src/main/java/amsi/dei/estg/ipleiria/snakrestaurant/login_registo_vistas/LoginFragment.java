package amsi.dei.estg.ipleiria.snakrestaurant.login_registo_vistas;

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
import amsi.dei.estg.ipleiria.snakrestaurant.main_application.MainMenuActivity;

public class LoginFragment extends Fragment {

    private TextView et_username, et_password;
    private Button button_signin, button_signup;

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
    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_signin:
                    if (TextUtils.isEmpty(et_username.getText().toString()) || TextUtils.isEmpty(et_password.getText().toString())){
                        String message = "Todos os campos devem ser preenchidos";
                        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                    }else{
                        Intent intent = new Intent(getContext(), MainMenuActivity.class);
                        startActivity(intent);
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