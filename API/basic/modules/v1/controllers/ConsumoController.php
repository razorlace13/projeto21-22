<?php

namespace app\modules\v1\controllers;

use app\models\User;
use yii\filters\auth\HttpBasicAuth;
use yii\rest\ActiveController;

class ConsumoController extends ActiveController
{
    public $modelClass = 'app\models\Consumo';
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

        $name=\Yii::$app -> request -> post('name');

        $Consumosmodel = new $this -> modelClass;
        $Consumosmodel -> name = $name;

        $ret = $Consumosmodel -> save(false);
        return ['SaveError' => $ret];
    }

    //http://localhost:8888/v1/consumo/delete/id

    public function actionDelete($id)
    {
        $Consumosmodel = new $this->modelClass;
        $ret=$Consumosmodel->deleteAll("id=".$id);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }
}
