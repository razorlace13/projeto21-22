<?php

namespace app\controllers;

use app\models\LoginForm;
use app\models\Products;
use app\models\ProductsSearch;
use yii\filters\Cors;
use yii\rest\ActiveController;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\filters\auth\HttpBasicAuth;
use yii\web\Response;

/**
 * ProductsController implements the CRUD actions for Products model.
 */
class ProductsController extends ActiveController
{
    public $modelClass = 'app\models\Products';
    public function init()
    {
        parent::init();
        \Yii::$app->user->enableSession = false;
    }
    public function behaviors()
    {
        $behaviors = parent::behaviors();

        $behaviors ['corsFilter'] = [
            'class' => Cors::className(),];

        $behaviors ['format'] = [
          'class' => 'yii\filters\ContentNegotiator',
            'formats' =>[
                'application/json' => Response::FORMAT_JSON,
            ],
        ];
        $behaviors['authenticator'] = [
            'class' => HttpBasicAuth::class,
        ];
        return $behaviors;
    }
    public function actionLogin()
    {
        $Productsmodel = new LoginForm();
        if ($Productsmodel->load(\Yii::$app->request->post(),'') && $Productsmodel->login()){
            return true;
        }
    }

    public function actionTotal(){
        $Productsmodel = new $this -> modelClass;
        $recs = $Productsmodel::find() -> all();
        return ['total' => count($recs)];
    }

    //http://localhost:8080/produto/set/3

    public function actionSet($limit){
        $Productsmodel = new $this -> modelClass;
        $rec = $Productsmodel::find() -> limit($limit) -> all();
        return ['limite' => $limit, 'Records' => $rec ];
    }

// http://localhost:8080/produto/post

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

    //http://localhost:8080/produto/delete/id

    public function actionDelete($id)
    {
        $Productsmodel = new $this->modelClass;
        $ret=$Productsmodel->deleteAll("id=".$id);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }

}
