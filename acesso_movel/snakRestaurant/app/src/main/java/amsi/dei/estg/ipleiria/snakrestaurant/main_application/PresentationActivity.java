package amsi.dei.estg.ipleiria.snakrestaurant.main_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas.MainActivity;
import amsi.dei.estg.ipleiria.snakrestaurant.main_application.MainMenuActivity;
import amsi.dei.estg.ipleiria.snakrestaurant.models.BDHelper;
import amsi.dei.estg.ipleiria.snakrestaurant.models.SingletonGestor;


public class PresentationActivity extends AppCompatActivity {

    ImageView logo,splashImg;
    LottieAnimationView lottieAnimationView;
    TextView appName;
    BDHelper bd;
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
        bd = new BDHelper(this);
        //SingletonGestor.getInstance(getApplicationContext()).getUserAPI(getApplicationContext(),true);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        },2700);

    }
}
