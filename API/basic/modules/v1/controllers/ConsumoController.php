<?php

namespace app\modules\v1\controllers;

use app\models\Consumo;
use app\models\User;
use yii\filters\auth\HttpBasicAuth;
use yii\filters\auth\QueryParamAuth;
use yii\rest\ActiveController;

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

    //http://localhost:8888/v1/consumo/set/3

    public function actionSet($limit){
        $Consumosmodel = new $this -> modelClass;
        $rec = $Consumosmodel::find() -> limit($limit) -> all();
        return ['limite' => $limit, 'Records' => $rec ];
    }

// http://localhost:8888/v1/consumo/post

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

    //http://localhost:8888/v1/consumo/delete/id

    public function actionDelete($id_consumo)
    {
        $Consumosmodel = new $this->modelClass;
        $ret=$Consumosmodel->deleteAll("id_consumo=".$id_consumo);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
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
