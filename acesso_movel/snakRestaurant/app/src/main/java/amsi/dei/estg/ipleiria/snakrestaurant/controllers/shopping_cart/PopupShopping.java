package amsi.dei.estg.ipleiria.snakrestaurant.controllers.shopping_cart;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.net.CacheResponse;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import amsi.dei.estg.ipleiria.snakrestaurant.Connections.Connections;
import amsi.dei.estg.ipleiria.snakrestaurant.R;
import amsi.dei.estg.ipleiria.snakrestaurant.adaptadores.RecyclerShoppingAdapter;
import amsi.dei.estg.ipleiria.snakrestaurant.controllers.login_registo_vistas.LoginFragment;
import amsi.dei.estg.ipleiria.snakrestaurant.listeners.PurchasePayListener;
import amsi.dei.estg.ipleiria.snakrestaurant.models.BDHelper;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Consumo;
import amsi.dei.estg.ipleiria.snakrestaurant.models.ConsumoPost;
import amsi.dei.estg.ipleiria.snakrestaurant.models.LoginSingleton;
import amsi.dei.estg.ipleiria.snakrestaurant.models.Shopping_card;
import amsi.dei.estg.ipleiria.snakrestaurant.models.SingletonGestor;

public class PopupShopping extends Activity implements PurchasePayListener {

    TextView price;
    EditText edit_Mesa;
    Button buy_btn;
    // String para a compra(purchases)
    String str_valor, str_data,str_id_user, str_mesa;
    // String para o consumo
    String str_id_pedido, str_id_product, str_quantidade;
    BDHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_purchase);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        databaseHelper = new BDHelper(getApplicationContext());

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.5));
        edit_Mesa = findViewById(R.id.edit_Mesa);
        price = findViewById(R.id.txtPrice);
        String typeofbuy = getIntent().getStringExtra("type");

        if (typeofbuy.equals("0")) {
            String price_from_fragment = getIntent().getStringExtra("price");
            price.setText(price_from_fragment);
        }else {
            ArrayList<Shopping_card> shopping_cards = databaseHelper.getAllShopingCard();
            ArrayList<ConsumoPost> consumoArray= new ArrayList<>();
            int priceall = 0;
            for (int i=0;shopping_cards.size() != i; i++){
                int temp = (int) shopping_cards.get(i).getPrice_shopping();
                priceall = priceall + temp;
            }
            price.setText("" + priceall);
        }
        buy_btn = findViewById(R.id.buy_btn);
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy(typeofbuy);
            }
        });

    }
    public void buy(String type){
            str_valor = this.price.getText().toString().trim();
            str_data = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            str_id_user = String.valueOf(Connections.id);
            str_mesa = this.edit_Mesa.getText().toString().trim();
            SingletonGestor.getInstance(this).setPurchasePayListener(this);
            SingletonGestor.getInstance(getApplicationContext()).PostPurchase(getApplicationContext(), str_valor, str_data, str_id_user, str_mesa, type);

    }


    private void buyConsumo(String type) {
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_MULTI_PROCESS);
        str_id_pedido = String.valueOf(sharedPreferences.getString("value",""));
        if (type.equals("0")){
            str_id_product = getIntent().getStringExtra("id_product");
            str_quantidade = getIntent().getStringExtra("quantidade");
            SingletonGestor.getInstance(getApplicationContext()).PostConsumo(getApplicationContext(), str_id_pedido, str_id_product, str_quantidade);
        }else{
            ArrayList<Shopping_card> shopping_cards = databaseHelper.getAllShopingCard();
            SingletonGestor.getInstance(getApplicationContext()).PostConsumoall(getApplicationContext(),str_id_pedido, shopping_cards);
        }

        delete(type);
    }
    public void delete(String typeofbuy){

        BDHelper bdHelper = new BDHelper(getApplicationContext());
        if (typeofbuy.equals("0")){
            String id_product_from_fragment = getIntent().getStringExtra("id_product");
            String str = "DELETE FROM '" + BDHelper.TABELA5 + "' WHERE id_product_shopping = '" + id_product_from_fragment + "'" ;
            bdHelper.basedados.execSQL(str);
        }else {
            String str = "DELETE FROM '" + BDHelper.TABELA5 + "'";
            bdHelper.basedados.execSQL(str);
        }
        finish();
        List<Shopping_card> shopping_card = bdHelper.getAllCard();

        if (shopping_card.size() > 0){
            RecyclerShoppingAdapter recyclerShoppingAdapter = new RecyclerShoppingAdapter(shopping_card, getApplicationContext());
            shopping_cart_Fragment.recyclerView.setAdapter(recyclerShoppingAdapter);
            //return;
        }else{
            RecyclerShoppingAdapter recyclerShoppingAdapter = new RecyclerShoppingAdapter(shopping_card, getApplicationContext());
            shopping_cart_Fragment.recyclerView.setAdapter(recyclerShoppingAdapter);
            Toast.makeText(getApplicationContext(), "sem dados ainda", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPayListener(String type) {
        buyConsumo(type);
    }
}
