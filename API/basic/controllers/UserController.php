<?php

namespace app\controllers;

use yii\filters\auth\HttpBasicAuth;
use yii\rest\ActiveController;

class UserController extends ActiveController
{
    public $modelClass = 'app\models\User';

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

        $user = \app\models\User::findByUsername($username);
        if ($user && $user->validatePassword($password_hash))
        {
            return $user;
        } return null;


    }


    //http://localhost:8888/user/total
    public function actionTotal(){
        $Usermodel = new $this -> modelClass;
        $recs = $Usermodel::find() -> all();
        return ['total' => count($recs)];
    }


    //http://localhost:8888/user/1/nif
    public function actionNif($id){
        $Usermodel = new $this -> modelClass;
        $rec = $Usermodel::find() -> where("id=".$id) -> one();

        if ($rec)
            return ['id' => $id, 'nif' => $rec -> nif ];
        return ['id' => $id, 'nif' => "null" ];
    }
    //http://localhost:8888/user/1/username
    public function actionUsername($id){
        $Usermodel = new $this -> modelClass;
        $rec = $Usermodel::find() -> where("id=".$id) -> one();
        if ($rec)
            return ['id' => $id, 'username' => $rec -> username ];
        return ['id' => $id, 'username' => "null" ];
    }

    //http://localhost:8888/user/set/3

    public function actionSet($limit){
        $Usermodel = new $this -> modelClass;
        $rec = $Usermodel::find() -> limit($limit) -> all();
        return ['limite' => $limit, 'Records' => $rec ];
    }

// http://localhost:8888/user/post

    public function actionPost() {


        $username=\Yii::$app -> request -> post('username');
        $auth_key=\Yii::$app -> request -> post('auth_key');
        $status=\Yii::$app -> request -> post('status');
        $password_hash=\Yii::$app -> request -> post('password_hash');
        $email=\Yii::$app -> request -> post('email');
        $created_at=\Yii::$app -> request -> post('created_at');
        $updated_at=\Yii::$app -> request -> post('updated_at');
        $numero=\Yii::$app -> request -> post('numero');
        $nif=\Yii::$app -> request -> post('nif');

        $Usermodel = new $this -> modelClass;
        $Usermodel -> username = $username;
        $Usermodel -> auth_key = $auth_key;
        $Usermodel -> status = $status;
        $Usermodel -> password_hash = $password_hash;
        $Usermodel -> email = $email;
        $Usermodel -> created_at = $created_at;
        $Usermodel -> updated_at = $updated_at;
        $Usermodel -> numero = $numero;
        $Usermodel -> nif = $nif;



        $ret = $Usermodel -> save(false);
        return ['SaveError' => $ret];
    }

    //http://localhost:8888/user/delete/id

    public function actionDelete($id)
    {
        $Usermodel = new $this->modelClass;
        $ret=$Usermodel->deleteAll("id=".$id);
        if($ret)
            return ['DelError' => $ret];
        throw new \yii\web\NotFoundHttpException("Client id not found!");
    }


}
