<?php

namespace app\modules\v1\controllers;

use app\models\User;
use yii\filters\auth\HttpBasicAuth;
use yii\rest\ActiveController;

class ProductsController extends ActiveController
{
    public $modelClass = 'app\models\Products';
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

    public function actionTotal(){
        $Productsmodel = new $this -> modelClass;
        $recs = $Productsmodel::find() -> all();
        return ['total' => count($recs)];
    }

    //http://localhost:8888/v1/produto/set/3

    public function actionSet($limit){
        $Productsmodel = new $this -> modelClass;
        $rec = $Productsmodel::find() -> limit($limit) -> all();
        return ['limite' => $limit, 'Records' => $rec ];
    }

// http://localhost:8888/v1/produto/post

    public function actionPost() {

        $name=\Yii::$app -> request -> post('name');
        $price=\Yii::$app -> request -> post('price');
        $id_category=\Yii::$app -> request -> post('id_category');

        $Productsmodel = new $this -> modelClass;
        $Productsmodel -> name = $name;
        $Productsmodel -> price = $price;
        $Productsmodel -> id_category = $id_category;

        $ret = $Productsmodel -> save(false);
        return ['SaveError' => $ret];
    }

    //http://localhost:8888/v1/produto/delete/id

    public function actionDelete($id)
    {
        $Productsmodel = new $this->modelClass;
        $ret=$Productsmodel->deleteAll("id=".$id);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }

}
