<?php

namespace app\modules\v1\controllers;

use app\models\User;
use yii\filters\auth\HttpBasicAuth;
use yii\rest\ActiveController;

class CategoryController extends ActiveController
{
    public $modelClass = 'app\models\Category';
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
        $Categorysmodel = new $this -> modelClass;
        $recs = $Categorysmodel::find() -> all();
        return ['total' => count($recs)];
    }

    //http://localhost:8888/v1/category/set/3

    public function actionSet($limit){
        $Categorysmodel = new $this -> modelClass;
        $rec = $Categorysmodel::find() -> limit($limit) -> all();
        return ['limite' => $limit, 'Records' => $rec ];
    }

// http://localhost:8888/v1/category/post

    public function actionPost() {

        $name=\Yii::$app -> request -> post('name');

        $Categorysmodel = new $this -> modelClass;
        $Categorysmodel -> name = $name;

        $ret = $Categorysmodel -> save(false);
        return ['SaveError' => $ret];
    }

    //http://localhost:8888/v1/category/delete/id

    public function actionDelete($id)
    {
        $Categorysmodel = new $this->modelClass;
        $ret=$Categorysmodel->deleteAll("id=".$id);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }
}