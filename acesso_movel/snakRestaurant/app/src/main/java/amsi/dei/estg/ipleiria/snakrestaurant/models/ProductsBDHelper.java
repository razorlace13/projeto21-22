package amsi.dei.estg.ipleiria.snakrestaurant.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ProductsBDHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "projeto21_22";
    private static final int VERSAO_BD = 1;
    //dados da tabela
    private static final String TABELA = "products";
    private static final String ID_PRODUCT = "id_product";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String ID_CATEGORY = "id_category";


    private final SQLiteDatabase basedados;

    public ProductsBDHelper(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
        basedados = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabela = "CREATE TABLE " + TABELA + "(" +
                ID_PRODUCT + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT NOT NULL, " +
                PRICE + " INTEGER NOT NULL, " +
                ID_CATEGORY + " INTEGER NOT NULL "+
                ")";

        db.execSQL(sqlTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        this.onCreate(db);
    }

    public ArrayList<Products> getAllProducts(){
        ArrayList<Products> lista = new ArrayList<>();

        Cursor cursor = this.basedados.query(TABELA,
                new String [] {ID_PRODUCT, NAME, PRICE, ID_CATEGORY},
                null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Products products = new Products (cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getInt(3));

                lista.add(products);

            }while(cursor.moveToNext());
        }
        return lista;
    }

}
