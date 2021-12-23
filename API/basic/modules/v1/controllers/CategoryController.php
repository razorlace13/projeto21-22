<?php

namespace app\modules\v1\controllers;

use app\models\User;
use yii\filters\auth\HttpBasicAuth;
use yii\filters\auth\QueryParamAuth;
use yii\rest\ActiveController;

class CategoryController extends ActiveController
{
    public $modelClass = 'app\models\Category';
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
    public function actionPut($id){

        $name=\Yii::$app -> request -> post('name');

        $Productsmodel = new $this->modelClass;
        $rec = $Productsmodel::find()->where('id_category = '.$id)->one();

        $rec->name = $name;

        $rec->save(false);
        return ['SaveError1' => $rec];
        //throw new \yii\web\NotFoundHttpException("Client id not found!");
    }

    //http://localhost:8888/v1/category/delete/id

    public function actionDelete($id_category)
    {
        $Categorysmodel = new $this->modelClass;
        $ret=$Categorysmodel->deleteAll("id_category=".$id_category);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }
}
