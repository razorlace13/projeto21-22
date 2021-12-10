package amsi.dei.estg.ipleiria.snakrestaurant.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;

public class BDHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "projeto21_22";
    private static final int VERSAO_BD = 2;
    //dados da tabela
    private static final String TABELA = "products", ID_PRODUCT = "id_product",NAME = "name",PRICE = "price",ID_CATEGORY = "id_category";
    private static final String TABELA1 = "consumo", ID_CONSUMO = "id_consumo",ID_PEDIDO = "id_pedido ",QUANTIDADE = "quantidade";
    private static final String TABELA3 = "purchases", ID_PURCHASES = "id_purchase", VALOR = "valor", DATA = "data", MESA = "mesa", ID_USER = "id_user";
    private static final String TABELA4 = "login", TOKEN = "token", USERNAME = "username", EMAIL = "email";

    private final SQLiteDatabase basedados;

    public BDHelper(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
        basedados = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabela = "CREATE TABLE " + TABELA + "(" +
                ID_PRODUCT + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT NOT NULL, " +
                PRICE + " INTEGER NOT NULL, " +
                ID_CATEGORY + " INTEGER NOT NULL)";

        db.execSQL(sqlTabela);
        sqlTabela = "CREATE TABLE " + TABELA1 + "(" +
                ID_CONSUMO + " INTEGER PRIMARY KEY, " +
                ID_PEDIDO + " INTEGER NOT NULL, " +
                ID_PRODUCT + " INTEGER NOT NULL, " +
                QUANTIDADE + " INTEGER NOT NULL)";

        db.execSQL(sqlTabela);
        sqlTabela = "CREATE TABLE " + TABELA3 + "(" +
                ID_PURCHASES + " INTEGER PRIMARY KEY, " +
                VALOR + " INTEGER NOT NULL, " +
                DATA + " INTEGER NOT NULL, " +
                MESA + " INTEGER NOT NULL, " +
                ID_USER + " INTEGER NOT NULL)";

        db.execSQL(sqlTabela);
        sqlTabela = "CREATE TABLE " + TABELA4 + "(" +
                TOKEN + " TEXT NOT NULL, " +
                USERNAME + " VARCHAR(100) NOT NULL, " +
                EMAIL + " VARCHAR(100) NOT NULL)";

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

    //para adicionar depois de ir ao adicionarProductsBD de baixo
    public void adicionarProductsBD(Products products){
        ContentValues valores = new ContentValues();

        valores.put(ID_CATEGORY, products.getId_product());
        valores.put(NAME, products.getName());
        valores.put(PRICE, products.getPrice());
        valores.put(ID_CATEGORY, products.getId_category());

        long id = basedados.insert(TABELA,null, valores);

        if (id != -1){
            products.setId_product(id);
        }
    }
    public void adicionarProductsBD(ArrayList<Products> productos){
        basedados.delete(TABELA,ID_PRODUCT, null);
        for (Products p:productos) {
            adicionarProductsBD(p);
        }
    }

    public LinkedList<Login> getUser() {
        LinkedList<Login> login = new LinkedList<>();
        Cursor cursor = this.basedados.rawQuery("SELECT * FROM login",
                null);
        if (cursor.moveToFirst()) {
            do {
                login.add(new Login(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2)
                ));
            } while (cursor.moveToNext());
        }
        return login;
    }

    public void inserirDadosLogin(Login login) {
        ContentValues values = new ContentValues();
        values.put("token", login.getToken());
        values.put("username", login.getUsername());
        values.put("email", login.getEmail());

        if(!verificarLogin(login, values)){
            basedados.insert("login", null, values);
        }

    }

    private boolean verificarLogin(Login login, ContentValues values) {
        return this.basedados.update("login",values, "token = ?", new String[]{"" + login.getToken()}) > 0;
    }

    public boolean removerUserDB(){
        return basedados.delete(TABELA4,null, null) == 1;
    }
}
