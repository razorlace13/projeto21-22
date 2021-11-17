package amsi.dei.estg.ipleiria.snakrestaurant.controllers.Products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import amsi.dei.estg.ipleiria.snakrestaurant.R;

public class Detalhe_produto extends AppCompatActivity {

    TextView txt_id,txt_name, txt_price,txt_idcategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_produto);

        txt_name = findViewById(R.id.txt_name);
        txt_price = findViewById(R.id.txt_price);
        txt_idcategory = findViewById(R.id.txt_idcategory);
    }
}