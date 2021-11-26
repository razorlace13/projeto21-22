package amsi.dei.estg.ipleiria.snakrestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas.LoginFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.main_application.MainMenuActivity;


public class PresentationActivity extends AppCompatActivity {

    ImageView logo,splashImg;
    LottieAnimationView lottieAnimationView;
    TextView appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_presentation);

        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.app_name);
        splashImg = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.Lottie);

        splashImg.animate().translationY(1600).setDuration(1000).setStartDelay(1500);
        logo.animate().translationY(-1000).setDuration(1000).setStartDelay(1500);
        appName.animate().translationY(-1000).setDuration(1000).setStartDelay(1500);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(1500);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        },2700);

    }

}
