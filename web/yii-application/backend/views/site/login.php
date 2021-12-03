<?php

/* @var $this yii\web\View */
/* @var $form yii\bootstrap\ActiveForm */
/* @var $model \common\models\LoginForm */

use yii\bootstrap4\ActiveForm;
use yii\helpers\Html;

$this->title = 'Login';

$fieldOptions1 = [
    'options' => ['class' => 'form-group has-feedback'],
    'inputTemplate' => "{input}<span class='glyphicon glyphicon-envelope form-control-feedback'></span>"
];

$fieldOptions2 = [
    'options' => ['class' => 'form-group has-feedback'],
    'inputTemplate' => "{input}<span class='glyphicon glyphicon-lock form-control-feedback'></span>"
];
?>
<div class="site-login">
    <div class="row">
        <div class="login-box">
            <div class="col-md-12 box box-radius">
                <h1><?= Html::encode($this->title) ?></h1>
                <p>Backoffice only for authorized persons</p>

                <?php $form = ActiveForm::begin(['id' => 'login-form', 'enableClientValidation' => false]); ?>

                <?= $form
                    ->field($model, 'username', $fieldOptions1)
                    ->label('username')
                    ->textInput(['placeholder' => $model->getAttributeLabel('username')]) ?>

                <?= $form
                    ->field($model, 'password', $fieldOptions2)
                    ->label('password')
                    ->passwordInput(['placeholder' => $model->getAttributeLabel('password')]) ?>

                <div class="form-group">
                    <?= Html::submitButton('Login', ['class' => 'btn btn-primary', 'name' => 'login-button']) ?>
                </div>
                <br>

                    <?php ActiveForm::end(); ?>
            </div>
        </div>
    </div>
</div>