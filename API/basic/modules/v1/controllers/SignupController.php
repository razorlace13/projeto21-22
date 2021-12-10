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

    public function actionPost()
    {
        $username=\Yii::$app -> request -> post('username');
        $auth_key=\Yii::$app -> request -> post('auth_key');
        $status=\Yii::$app -> request -> post('status');
        $password=\Yii::$app -> request -> post('password_hash');
        $email=\Yii::$app -> request -> post('email');
        $created_at=\Yii::$app -> request -> post('created_at');
        $updated_at=\Yii::$app -> request -> post('updated_at');
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