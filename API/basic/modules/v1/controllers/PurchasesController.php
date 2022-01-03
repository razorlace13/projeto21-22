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

    //GET
    //http://192.168.1.189:1884/v1/purchases?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    //POST
    //http://192.168.1.189:1884/v1/purchases/post?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    //PUT
    //http://192.168.1.189:1884/v1/purchases/put/1?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    //DELETE
    //http://192.168.1.189:1884/v1/purchases/delete/1?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    //CUSTOM
    //METHOD GET
    //http://192.168.1.189:1884/v1/purchases/purchasesuser/4?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    public function actionPost() {
        $valor =\Yii::$app -> request -> post('valor');
        $data =\Yii::$app -> request -> post('data');
        $mesa=\Yii::$app -> request -> post('mesa');
        $id_user=\Yii::$app -> request -> post('id_user');

        $Purchasesmodel = new $this -> modelClass;
        $Purchasesmodel -> valor  = $valor;
        $Purchasesmodel -> data = $data;
        $Purchasesmodel -> mesa = $mesa;
        $Purchasesmodel -> id_user = $id_user;

        $ret = $Purchasesmodel -> save(false);
        return ['SaveError' => $ret];
    }
    public function actionPut($id_purchase){

        $valor =\Yii::$app -> request -> post('valor');
        $data =\Yii::$app -> request -> post('data');
        $mesa=\Yii::$app -> request -> post('mesa');
        $id_user=\Yii::$app -> request -> post('id_user');

        $Purchasesmodel = new $this->modelClass;
        $rec = $Purchasesmodel::find()->where('id_purchase = '.$id_purchase)->one();

        $rec -> valor  = $valor;
        $rec -> data = $data;
        $rec -> mesa = $mesa;
        $rec -> id_user = $id_user;

        $rec->save(false);
        return ['SaveError1' => $rec];
        //throw new \yii\web\NotFoundHttpException("Client id not found!");
    }

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
