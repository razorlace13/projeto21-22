<?php

namespace app\modules\v1\controllers;

use app\models\User;
//use yii\filters\auth\HttpBasicAuth;
use yii\filters\auth\QueryParamAuth;
use yii\rest\ActiveController;

class PurchasesController extends ActiveController
{
    public $modelClass = 'app\models\Purchases';
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

    //http://localhost:8888/v1/purchases/total
    public function actionTotal(){
        $Purchasesmodel = new $this -> modelClass;
        $recs = $Purchasesmodel::find() -> all();
        return ['total' => count($recs)];
    }

    //http://localhost:8888/v1/purchases/set/3

    public function actionSet($limit){
        $Purchasesmodel = new $this -> modelClass;
        $rec = $Purchasesmodel::find() -> limit($limit) -> all();
        return ['limite' => $limit, 'Records' => $rec ];
    }

    // http://localhost:8888/v1/purchases/post

    public function actionPost() {
        //mudar
        $id_product =\Yii::$app -> request -> post('id_product ');
        $id_user=\Yii::$app -> request -> post('id_user');
        $mesa=\Yii::$app -> request -> post('mesa');

        $Purchasesmodel = new $this -> modelClass;
        $Purchasesmodel -> name = $id_product ;
        $Purchasesmodel -> price = $id_user;
        $Purchasesmodel -> mesa = $mesa;

        $ret = $Purchasesmodel -> save(false);
        return ['SaveError' => $ret];
    }
    public function actionPut($id_purchase){

        $valor =\Yii::$app -> request -> post('valor ');
        $data =\Yii::$app -> request -> post('data ');
        $id_product =\Yii::$app -> request -> post('id_product ');
        $id_user=\Yii::$app -> request -> post('id_user');
        $mesa=\Yii::$app -> request -> post('mesa');

        $Purchasesmodel = new $this->modelClass;
        $rec = $Purchasesmodel::find()->where('id_purchase = '.$id_purchase)->one();

        $rec -> valor  = $valor ;
        $rec -> data = $data;
        $rec -> id_product  = $id_product ;
        $rec -> id_user = $id_user;
        $rec -> mesa = $mesa;

        $rec->save(false);
        return ['SaveError1' => $rec];
        //throw new \yii\web\NotFoundHttpException("Client id not found!");
    }

    //http://localhost:8888/v1/purchases/delete/id

    public function actionDelete($id_purchase)
    {
        $Purchasesmodel = new $this->modelClass;
        $ret=$Purchasesmodel->deleteAll("id_purchase=".$id_purchase);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }

    public function actionPurchasesuser($id)
    {
            $Purchasesmodel = new $this->modelClass;
            $recs = $Purchasesmodel::find()->where('id_user = ' . $id)->all();
            return $recs;

    }

}
