<?php

namespace app\modules\v1\controllers;

use Yii;
use yii\rest\ActiveController;

class LoginController extends ActiveController
{
    public $modelClass = 'app\models\LoginForm';

    public function actionGet($username, $password)
    {

        $login = ['LoginForm' =>[
            'username' => $username,
            'password' => $password]
        ];

        $loginmodel = new $this->modelClass;

        if ($loginmodel->load($login) && $loginmodel->login()) {
            $user = Yii::$app->user->getIdentity();
            return [
                'username' => $user ->username,
                'email'    => $user ->email,
                'authkey'  => Yii::$app->user->getIdentity()->getAuthKey()];
        }

        return ['Login' => false];
    }

}