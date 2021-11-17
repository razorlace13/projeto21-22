package amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import amsi.dei.estg.ipleiria.snakrestaurant.R;



public class SignupFragment extends Fragment {

    private TextView et_username, et_password,et_confirm_password,et_phone,et_email;
    private Button button_signin, button_signup;

    public SignupFragment() {
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
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        et_email = view.findViewById(R.id.et_email);
        et_username = view.findViewById(R.id.et_username);
        et_phone = view.findViewById(R.id.et_phone);
        et_password = view.findViewById(R.id.et_password);
        et_confirm_password = view.findViewById(R.id.et_confirm_password);
        button_signin = view.findViewById(R.id.button_signin);
        button_signup = view.findViewById(R.id.button_signup);


        button_signin.setOnClickListener(mCorkyListener);

        return view;
    }
    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_signin:

                    Fragment fragmento = new LoginFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_frame, fragmento);
                    fragmentTransaction.commit();
                    break;
                case R.id.button_signup:

                    break;

                default:
                    break;
            }

        }
    };

}