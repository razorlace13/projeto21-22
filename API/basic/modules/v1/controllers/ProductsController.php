<?php

namespace app\modules\v1\controllers;

use app\models\User;
use yii\filters\auth\HttpBasicAuth;
use yii\filters\auth\QueryParamAuth;
use yii\rest\ActiveController;

class ProductsController extends ActiveController
{
    public $modelClass = 'app\models\Products';
    const noPermission = 'Access denied';

    public function actionIndex()
    {
        return $this->render('index');
    }

    public function actionTotal(){
        $Productsmodel = new $this -> modelClass;
        $recs = $Productsmodel::find() -> all();
        return ['total' => count($recs)];
    }

    public function actionFind_id_category($limit){
        $Productsmodel = new $this -> modelClass;
        $rec = $Productsmodel::find() -> where("id_category=".$limit) -> all();
        return ['limite' => $limit, 'Records' => $rec ];
    }


    public function actionSet($limit){
        $Productsmodel = new $this -> modelClass;
        $rec = $Productsmodel::find() -> limit($limit) -> all();
        return ['limite' => $limit, 'Records' => $rec ];
    }

    //GET
    //http://192.168.1.189:1884/v1/products?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    //POST
    //http://192.168.1.189:1884/v1/products/post?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    //PUT
    //http://192.168.1.189:1884/v1/products/put/1?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi

    //DELETE
    //http://192.168.1.189:1884/v1/products/delete/1?access-token=F_Fu2do9PM8hdn0LCX4_YPpTtDgsJIZi
    
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

    public function actionPut($id){

        $name=\Yii::$app -> request -> post('name');
        $price=\Yii::$app -> request -> post('price');
        $id_category=\Yii::$app -> request -> post('id_category');

        $Productsmodel = new $this->modelClass;
        $rec = $Productsmodel::find()->where('id_product = '.$id)->one();

            $rec->name = $name;
            $rec->price = $price;
            $rec->id_category = $id_category;

            $rec->save(false);
            return ['SaveError1' => $rec];
            //throw new \yii\web\NotFoundHttpException("Client id not found!");
    }


    public function actionDelete($id_product)
    {
        $Productsmodel = new $this->modelClass;
        $ret=$Productsmodel->deleteAll("id_product=".$id_product);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }

}
