package amsi.dei.estg.ipleiria.snakrestaurant.controllers.shopping_cart;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections;
import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.models.LoginSingleton;
import amsi.dei.estg.ipleiria.snakrestaurant.models.SingletonGestor;

public class PopupShopping extends Activity {

    TextView price;
    EditText edit_Mesa;
    Button buy_btn;
    String str_valor, str_data,str_id_user, str_mesa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_purchase);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        String price_from_fragment = getIntent().getStringExtra("price");

        edit_Mesa = findViewById(R.id.edit_Mesa);

        price = findViewById(R.id.txtPrice);
        price.setText(price_from_fragment);

        buy_btn = findViewById(R.id.buy_btn);
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy();
            }
        });

    }
    public void buy(){

        if(str_mesa.equals("")){
            Toast.makeText(this, "A mesa não pode estar vazia!", Toast.LENGTH_SHORT).show();
        }
        else {
            str_valor = this.price.getText().toString().trim();
            str_data = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            str_id_user = String.valueOf(Connections.id);
            str_mesa = this.edit_Mesa.getText().toString().trim();
            /*   new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            Toast.makeText(this, str_valor+"/", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, str_data+"/", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, str_id_user+"/", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, str_mesa+"/", Toast.LENGTH_SHORT).show();
            */
            SingletonGestor.getInstance(getApplicationContext()).PostPurchase(getApplicationContext(), str_valor, str_data, str_id_user, str_mesa);
        }
    }
}
