<?php

namespace app\modules\v1\controllers;

use app\models\Consumo;
use app\models\User;
use phpDocumentor\Reflection\Types\Array_;
use Yii;
use yii\filters\auth\HttpBasicAuth;
use yii\filters\auth\QueryParamAuth;
use yii\helpers\Console;
use yii\rest\ActiveController;
use function MongoDB\BSON\toJSON;

class ConsumoController extends ActiveController
{
    public $modelClass = 'app\models\Consumo';
    const noPermission = 'Access denied';

    public function behaviors()
    {
        $behaviors = parent::behaviors();
        $behaviors['authenticator'] = [
            'class' => QueryParamAuth::className(),
        ];

        return $behaviors;
    }
    public function auth($token) {

        $user = User::findIdentityByAccessToken($token);
        if ($user != null)
        {
            return $user;
        } return null;


    }

    public function actionIndex()
    {
        return $this->render('index');
    }
    public function actionTotal(){
        $Consumosmodel = new $this -> modelClass;
        $recs = $Consumosmodel::find() -> all();
        return ['total' => count($recs)];
    }

    //GET
    //http://192.168.1.189:1884/v1/consumo?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    //POST
    //http://192.168.1.189:1884/v1/consumo/post?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    //PUT
    //http://192.168.1.189:1884/v1/consumo/put/1?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    //DELETE
    //http://192.168.1.189:1884/v1/consumo/delete/1?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    //CUSTOM
    //METHOD GET
    //http://192.168.1.189:1884/v1/consumo/consumopedido/4?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    public function actionSet($limit){
        $Consumosmodel = new $this -> modelClass;
        $rec = $Consumosmodel::find() -> limit($limit) -> all();
        return ['limite' => $limit, 'Records' => $rec ];
    }

    

    public function actionPost() {

        $id_pedido=\Yii::$app -> request -> post('id_pedido');
        $id_product=\Yii::$app -> request -> post('id_product');
        $quantidade=\Yii::$app -> request -> post('quantidade');

        $Consumosmodel = new $this -> modelClass;
        $Consumosmodel -> id_pedido = $id_pedido;
        $Consumosmodel -> id_product = $id_product;
        $Consumosmodel -> quantidade = $quantidade;

        $ret = $Consumosmodel -> save(false);
        return ['SaveError' => $ret];
    }
    public function actionPut($id){

        $id_pedido=\Yii::$app -> request -> post('id_pedido');
        $id_product=\Yii::$app -> request -> post('id_product');
        $quantidade=\Yii::$app -> request -> post('quantidade');

        $Productsmodel = new $this->modelClass;
        $rec = $Productsmodel::find()->where('id_consumo = '.$id)->one();

        $rec->id_pedido = $id_pedido;
        $rec->id_product = $id_product;
        $rec->quantidade = $quantidade;

        $rec->save(false);
        return ['SaveError1' => $rec];
        //throw new \yii\web\NotFoundHttpException("Client id not found!");
    }

    public function actionDelete($id_consumo)
    {
        $Consumosmodel = new $this->modelClass;
        $ret=$Consumosmodel->deleteAll("id_consumo=".$id_consumo);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }

    public function actionConsumocompleto()
    {

        $consuomArray = \Yii::$app -> request -> post('array');

        $consuomArray = json_decode($consuomArray);

        foreach($consuomArray as $consumo)
        {
            $consumoObject = json_decode(json_encode($consumo), FALSE);
            $id_pedido=$consumoObject->id_pedido;
            $id_product=$consumoObject->id_product;
            $quantidade=$consumoObject->quantidade;

            $Consumosmodel = new $this -> modelClass;
            $Consumosmodel -> id_pedido = $id_pedido;
            $Consumosmodel -> id_product = $id_product;
            $Consumosmodel -> quantidade = $quantidade;

            $Consumosmodel -> save(false);
        }
        return ['teste' => $consuomArray];
    }

    public function actionConsumopedido($id)
    {

        $recs = (new \yii\db\Query())
            ->select(['consumo.id_consumo','consumo.id_pedido', 'products.name','consumo.quantidade'])
            ->from('consumo')
            ->innerjoin('products',"products.id_product = consumo.id_product")
            ->where(['id_pedido' => $id])
            ->all();
        return $recs;

    }
}
