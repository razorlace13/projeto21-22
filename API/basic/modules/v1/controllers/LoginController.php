<?php

namespace modules\v1\controllers;

use Yii;
use yii\rest\ActiveController;

class LoginController extends ActiveController
{
    public $modelClass = 'models\LoginForm';

    public function actionGet($username, $password)
    {

        $login = ['LoginForm' =>[
            'username' => $username,
            'password' => $password]
        ];

        $loginmodel = new $this->modelClass;

        if ($loginmodel->load($login) && $loginmodel->login()) {
            return ['Login' => true, 'authkey' => Yii::$app->user->getIdentity()->getAuthKey()];
        }

        return ['Login' => false];
    }

}