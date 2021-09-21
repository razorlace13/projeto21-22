<?php

namespace app\controllers;

use app\models\Category;
use app\models\CategorySearch;
use yii\rest\ActiveController;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * CategoryController implements the CRUD actions for Category model.
 */
class CategoryController extends ActiveController
{
    public $modelClass = 'app\models\Category';

    public function actionTotal(){
        $Categorysmodel = new $this -> modelClass;
        $recs = $Categorysmodel::find() -> all();
        return ['total' => count($recs)];
    }

    //http://localhost:8080/produto/set/3

    public function actionSet($limit){
        $Categorysmodel = new $this -> modelClass;
        $rec = $Categorysmodel::find() -> limit($limit) -> all();
        return ['limite' => $limit, 'Records' => $rec ];
    }

// http://localhost:8080/produto/post

    public function actionPost() {

        $name=\Yii::$app -> request -> post('name');

        $Categorysmodel = new $this -> modelClass;
        $Categorysmodel -> name = $name;

        $ret = $Categorysmodel -> save(false);
        return ['SaveError' => $ret];
    }

    //http://localhost:8080/produto/delete/id

    public function actionDelete($id)
    {
        $Categorysmodel = new $this->modelClass;
        $ret=$Categorysmodel->deleteAll("id=".$id);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }
}
