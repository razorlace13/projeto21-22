<?php

namespace app\modules\v1\controllers;

use app\models\AuthAssignment;
use app\models\SignupForm;
use app\models\User;
use Yii;
use yii\rest\ActiveController;

class SignupController extends ActiveController
{
    public $modelClass = 'app\models\User';

    //CUSTOM
    //METHOD POST
    //http://192.168.1.189:1884/v1/signup/post

    public function actionPost()
    {
        $username=\Yii::$app -> request -> post('username');
        $password=\Yii::$app -> request -> post('password_hash');
        $email=\Yii::$app -> request -> post('email');
        $numero=\Yii::$app -> request -> post('numero');
        $nif=\Yii::$app -> request -> post('nif');

        $Usermodel = new $this -> modelClass;
        $Usermodel -> username = $username;
        $Usermodel -> email = $email;
        $Usermodel -> numero = $numero;
        $Usermodel -> nif = $nif;
        $Usermodel->status = 10;
        $Usermodel ->setPassword($password);
        $Usermodel->generateAuthKey();
        $Usermodel->generateEmailVerificationToken();
        $Usermodel->created_at = date('d-m-y');

        $Usermodel -> save(false);

        if ($Usermodel -> save(false)){
            return 'Utilizador guardado';
        }
        else {
                return 'Utilizador não guardado';
        }
    }

}