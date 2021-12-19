package amsi.dei.estg.ipleiria.snakrestaurant.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BDHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "projeto21_22";
    private static final int VERSAO_BD = 11;
    //dados da tabela
    private static final String TABELA = "products", ID_PRODUCT = "id_product",NAME = "name",PRICE = "price",ID_CATEGORY = "id_category";
    private static final String TABELA1 = "consumo", ID_CONSUMO = "id_consumo",ID_PEDIDO = "id_pedido ",QUANTIDADE = "quantidade";
    private static final String TABELA2 = "user", NUMERO = "numero";
    private static final String TABELA3 = "purchases", ID_PURCHASES = "id_purchase", VALOR = "valor", DATA = "data", MESA = "mesa", ID_USER = "id_user";
    private static final String TABELA4 = "login", ID = "id", TOKEN = "token", USERNAME = "username", EMAIL = "email";
    private static final String TABELA5 = "shopping_cart",ID_SHOPPING= "id_shopping", ID_PRODUCT_SHOPPING = "id_product_shopping",NAME_SHOPPING = "name_shopping",PRICE_SHOPPING = "price_shopping",ID_CATEGORY_SHOPPING = "id_category_shopping";
    private SQLiteDatabase basedados;

    public BDHelper(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
        basedados = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Produtos
        String sqlTabela = "CREATE TABLE " + TABELA + "(" +
                ID_PRODUCT + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT NOT NULL, " +
                PRICE + " INTEGER NOT NULL, " +
                ID_CATEGORY + " INTEGER NOT NULL)";

        db.execSQL(sqlTabela);
        //Consumo
        sqlTabela = "CREATE TABLE " + TABELA1 + "(" +
                ID_CONSUMO + " INTEGER PRIMARY KEY, " +
                ID_PEDIDO + " INTEGER NOT NULL, " +
                NAME + " String NOT NULL, " +
                QUANTIDADE + " INTEGER NOT NULL)";

        db.execSQL(sqlTabela);
        //User
        sqlTabela = "CREATE TABLE " + TABELA2 + "(" +
                ID + " INTEGER PRIMARY KEY, " +
                USERNAME + " TEXT NOT NULL, " +
                EMAIL + " INTEGER NOT NULL, " +
                NUMERO + " INTEGER NOT NULL)";

        db.execSQL(sqlTabela);
        //Purchases
        sqlTabela = "CREATE TABLE " + TABELA3 + "(" +
                ID_PURCHASES + " INT NOT NULL, " +
                VALOR + " INTEGER NOT NULL, " +
                DATA + " STRING NOT NULL, " +
                MESA + " INTEGER NOT NULL, " +
                ID_USER + " INTEGER NOT NULL)";

        db.execSQL(sqlTabela);
        //login
        sqlTabela = "CREATE TABLE " + TABELA4 + "(" +
                ID + " INT NOT NULL, " +
                TOKEN + " TEXT NOT NULL, " +
                USERNAME + " VARCHAR(100) NOT NULL, " +
                EMAIL + " VARCHAR(100) NOT NULL)";

        db.execSQL(sqlTabela);
        //Shopping Cart
         sqlTabela = "CREATE TABLE " + TABELA5 + "(" +
                 ID_SHOPPING + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                 ID_PRODUCT_SHOPPING + " INTEGER NOT NULL," +
                 NAME_SHOPPING + " TEXT NOT NULL, " +
                 PRICE_SHOPPING + " INTEGER NOT NULL, " +
                 ID_CATEGORY_SHOPPING + " INTEGER NOT NULL," +
                 QUANTIDADE + " INTEGER NOT NULL)";

        db.execSQL(sqlTabela);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA1);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA2);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA3);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA4);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA5);
        this.onCreate(db);
    }
    public boolean getUserProfileCheck(){
        Cursor cursor = this.basedados.query(TABELA2,
                new String [] {ID, USERNAME, EMAIL, NUMERO},
                null, null, null, null, null);
        if (cursor.moveToFirst()){
            return true;
        }
        return false;
    }
    public User getUserProfile(){
        Cursor cursor = this.basedados.query(TABELA2,
                new String [] {ID, USERNAME, EMAIL, NUMERO},
                null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                User user = new User (cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getInt(3));
                return user;
            }while(cursor.moveToNext());
        }
        return null;
    }
    public boolean getProductsCheck(){
        Cursor cursor = this.basedados.query(TABELA,
                new String [] {ID_PRODUCT, NAME, PRICE, ID_CATEGORY},
                null, null, null, null, null);
        if (cursor.moveToFirst()){
            return true;
        }
        return false;
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
    public boolean getConsumoCheck(int id){
        Cursor cursor = this.basedados.query(TABELA1,
                new String [] {ID_CONSUMO, ID_PEDIDO, NAME, QUANTIDADE},
                ID_PEDIDO + "=" + id, null, null, null, null);
        if (cursor.moveToFirst()){
            return true;
        }
        return false;
    }
    public ArrayList<Consumo> getAllConsumo(int id){
        ArrayList<Consumo> lista = new ArrayList<>();

        Cursor cursor = this.basedados.query(TABELA1,
                new String [] {ID_CONSUMO, ID_PEDIDO, NAME, QUANTIDADE},
                ID_PEDIDO + "=" + id, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Consumo consumo = new Consumo (cursor.getInt(0), cursor.getInt(1),
                        cursor.getString(2), cursor.getInt(3));


                lista.add(consumo);


            }while(cursor.moveToNext());
        }
        return lista;
    }
    public boolean getPurchasesCheck(){
        Cursor cursor = this.basedados.query(TABELA3,
                new String [] {ID_PURCHASES, VALOR, MESA, DATA,ID_USER},
                null, null, null, null, null, null);

        if (cursor.moveToFirst()){
            return true;
        }
        return false;
    }
    public ArrayList<Purchases> getAllPurchases(){
        ArrayList<Purchases> lista = new ArrayList<>();

        Cursor cursor = this.basedados.query(TABELA3,
                new String [] {ID_PURCHASES, VALOR, MESA, DATA,ID_USER},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Purchases purchases = new Purchases (cursor.getInt(0), cursor.getDouble(1),
                        cursor.getString(3),cursor.getInt(2),  cursor.getInt(4));

                lista.add(purchases);

            }while(cursor.moveToNext());
        }
        return lista;
    }
    public Purchases getOnePurchases(int position){
        int count= 0;
        Cursor cursor = this.basedados.query(TABELA3,
                new String [] {ID_PURCHASES, VALOR, MESA, DATA,ID_USER},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                if (count == position) {
                    Purchases purchases = new Purchases(cursor.getInt(0), cursor.getDouble(1),
                            cursor.getString(3), cursor.getInt(2), cursor.getInt(4));
                    return purchases;
                }
                count++;
            }while(cursor.moveToNext());
        }
        return null;
    }
    public void adicionarUserProfileBD(User user){
        basedados.delete(TABELA2,ID, null);
        ContentValues valores = new ContentValues();

        valores.put(USERNAME, user.getUsername());
        valores.put(EMAIL, user.getEmail());
        valores.put(NUMERO, user.getNumero());
        valores.put(ID, user.getId());
        long id = basedados.insert(TABELA2,null, valores);

        if (id != -1){
            user.setId((int) id);
        }
    }
    public void adicionarProductsBD(Products products){
        ContentValues valores = new ContentValues();

        valores.put(ID_PRODUCT, products.getId_product());
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
    public void adicionarConsumoBD(Consumo consumo){
        ContentValues valores = new ContentValues();

        valores.put(ID_CONSUMO, consumo.getId_consumo());
        valores.put(ID_PEDIDO, consumo.getId_pedido());
        valores.put(NAME, consumo.getProduct());
        valores.put(QUANTIDADE, consumo.getQuantidade());

        long id = basedados.insert(TABELA1,null, valores);

        if (id != -1){
            consumo.setId_consumo(id);
        }
    }
    public void adicionarConsumoBD(ArrayList<Consumo> consumo){
        for (Consumo p:consumo) {
            adicionarConsumoBD(p);
        }
    }
    public void adicionarPurchasesBD(Purchases purchases){
        ContentValues valores = new ContentValues();

        valores.put(ID_PURCHASES, purchases.getId_purchase());
        valores.put(VALOR, purchases.getValor());
        valores.put(DATA, purchases.getData());
        valores.put(MESA, purchases.getMesa());
        valores.put(ID_USER, purchases.getId_user());

        long id = basedados.insert(TABELA3,null, valores);

        if (id != -1){
            purchases.setId_purchase(id);
        }
    }
    public void adicionarPurchasesBD(ArrayList<Purchases> purchases){
        basedados.delete(TABELA3,ID_PURCHASES, null);
        for (Purchases p:purchases) {
            adicionarPurchasesBD(p);
        }
    }
    public LinkedList<Login> getUser() {
        LinkedList<Login> login = new LinkedList<>();
        Cursor cursor = this.basedados.rawQuery("SELECT * FROM login",
                null);
        if (cursor.moveToFirst()) {
            do {
                login.add(new Login(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }
        return login;
    }
    public void inserirDadosLogin(Login login) {
        ContentValues values = new ContentValues();
        values.put("id", login.getId());
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

    public List<Shopping_card> getAllCard() {
        String sql = "select * from " + TABELA5;
        basedados = this.getReadableDatabase();
        List<Shopping_card> store = new ArrayList<>();

        Cursor cursor = basedados.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                long id_product_shopping = Integer.parseInt(String.valueOf(cursor.getInt(0)));
                String name_shopping = cursor.getString(1);
                int price_shopping = Integer.parseInt(String.valueOf(cursor.getInt(2)));
                int id_category_shopping = Integer.parseInt(String.valueOf(cursor.getInt(3)));
                int quantidade_shopping = Integer.parseInt(String.valueOf(cursor.getInt(4)));

                store.add(new Shopping_card(id_product_shopping, name_shopping, price_shopping,id_category_shopping,quantidade_shopping));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return store;
    }

    public Shopping_card add_to_card(Shopping_card shopping_card){
        ContentValues contentValues = new ContentValues();

        //contentValues.put(BDHelper.ID_SHOPPING, shopping_card.getId_shopping());
        contentValues.put(BDHelper.ID_PRODUCT_SHOPPING, shopping_card.getId_product_shopping());
        contentValues.put(BDHelper.NAME_SHOPPING, shopping_card.getName_shopping());
        contentValues.put(BDHelper.PRICE_SHOPPING, shopping_card.getPrice_shopping());
        contentValues.put(BDHelper.ID_CATEGORY_SHOPPING, shopping_card.getId_category_shopping());
        contentValues.put(BDHelper.QUANTIDADE, shopping_card.getQuantidade_shopping());

        basedados = this.getWritableDatabase();
        basedados.insert(BDHelper.TABELA5,null,contentValues);
        return shopping_card;
    }
    public void delete_from_card(int id){
        basedados = this.getWritableDatabase();
        basedados.delete(TABELA5,ID_SHOPPING + " = ?", new String[]
                {String.valueOf(id)});

    }
}
