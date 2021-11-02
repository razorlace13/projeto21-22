package amsi.dei.estg.ipleiria.snakrestaurant.main_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import amsi.dei.estg.ipleiria.snakrestaurant.R;


public class MainMenuActivity extends AppCompatActivity {

    BottomNavigationView bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        bottomBar = findViewById(R.id.bottomBar);

        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainMenuActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                Fragment fragmento = null;
                switch (item.getItemId()){
                    case R.id.home:
                        //fragmento = new EstaticoFragment();
                        //setTitle(item.getTitle());

                        break;
                    case R.id.products:
                        break;
                    case R.id.user:
                        Intent intent = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.shop:
                        break;
                }
                return true;
            }
        });

    }
}