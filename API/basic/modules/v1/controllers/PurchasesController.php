<?php

namespace app\modules\v1\controllers;

use app\models\User;
use yii\filters\auth\HttpBasicAuth;
use yii\rest\ActiveController;

class PurchasesController extends ActiveController
{
    public $modelClass = 'app\models\Purchases';
    public function behaviors()
    {
        $behaviors = parent::behaviors();
        $behaviors['authenticator'] = [
            'class' => HttpBasicAuth::className(),
            'auth' => [$this, 'auth']
        ];

        return $behaviors;
    }
    public function auth($username, $password_hash) {

        $user = User::findByUsername($username);
        if ($user && $user->validatePassword($password_hash))
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

        $Purchasesmodel = new $this -> modelClass;
        $Purchasesmodel -> name = $id_product ;
        $Purchasesmodel -> price = $id_user;

        $ret = $Purchasesmodel -> save(false);
        return ['SaveError' => $ret];
    }

    //http://localhost:8888/v1/purchases/delete/id

    public function actionDelete($id)
    {
        $Purchasesmodel = new $this->modelClass;
        $ret=$Purchasesmodel->deleteAll("id=".$id);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }
}
