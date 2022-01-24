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
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.Products.ProductsFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas.HomeFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas.MainActivity;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.profile.ProfileFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.shopping_cart.shopping_cart_Fragment;
import amsi.dei.estg.ipleiria.snakrestaurant.models.BDHelper;
import amsi.dei.estg.ipleiria.snakrestaurant.models.LoginSingleton;


public class MainMenuActivity extends AppCompatActivity {

    public BottomNavigationView bottomBar;
    private BDHelper bd;

    public static final int FRAGMENTO_LISTA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();
        bottomBar = findViewById(R.id.bottomBar);
        bd = new BDHelper(this);
        Fragment fragmento2 = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.MainMenuFL, fragmento2, fragmento2.getClass().getSimpleName()).commit();

        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainMenuActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                Fragment fragmento = null;
                switch (item.getItemId()){
                    case R.id.home:
                        mostrahome();
                        break;
                    case R.id.products:
                       mostraprodutos();

                        break;
                    case R.id.user:
                        mostraprofile();

                        break;
                    case R.id.shop:
                        mostracarrinho();
                        break;
                    case R.id.logout:
                        logout();
                        break;
                }
                return true;
            }
        });

    }

    private void logout() {
        bd.remover();
        //this.deleteDatabase("projeto21_22");
        this.finish();
    }

    private void mostraprodutos() {
        Fragment fragmento2 = new ProductsFragment();
         getSupportFragmentManager().beginTransaction().replace(R.id.MainMenuFL, fragmento2, fragmento2.getClass().getSimpleName()).commit();
    }

    private void mostraprofile() {
        Fragment fragmento2 = new ProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.MainMenuFL, fragmento2, fragmento2.getClass().getSimpleName()).commit();
    }
    private void mostracarrinho(){
        Fragment fragmento2 = new shopping_cart_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.MainMenuFL, fragmento2, fragmento2.getClass().getSimpleName()).commit();

    }
    private void mostrahome(){
        Fragment fragmento2 = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.MainMenuFL, fragmento2, fragmento2.getClass().getSimpleName()).commit();

    }


}