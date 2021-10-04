<?php

namespace app\controllers;

use app\models\LoginForm;
use app\models\Purchases;
use app\models\PurchasesSearch;
use yii\filters\Cors;
use yii\rest\ActiveController;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\filters\auth\HttpBasicAuth;
use yii\web\Response;

/**
 * PurchasesController implements the CRUD actions for Purchases model.
 */
class PurchasesController extends ActiveController
{
    public $modelClass = 'app\models\Purchases';
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
        $Purchasesmodel = new LoginForm();
        if ($Purchasesmodel->load(\Yii::$app->request->post(),'') && $Purchasesmodel->login()){
            return true;
        }
    }

    public function actionTotal(){
        $Purchasesmodel = new $this -> modelClass;
        $recs = $Purchasesmodel::find() -> all();
        return ['total' => count($recs)];
    }

    //http://localhost:8888/purchases/set/3

    public function actionSet($limit){
        $Purchasesmodel = new $this -> modelClass;
        $rec = $Purchasesmodel::find() -> limit($limit) -> all();
        return ['limite' => $limit, 'Records' => $rec ];
    }

// http://localhost:8888/purchases/post

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

    //http://localhost:8888/purchases/delete/id

    public function actionDelete($id)
    {
        $Purchasesmodel = new $this->modelClass;
        $ret=$Purchasesmodel->deleteAll("id=".$id);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }

}
