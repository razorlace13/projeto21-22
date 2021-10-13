<?php

use app\models\User;
use yii\helpers\Html;
use yii\web\View;
use yii\widgets\ActiveForm;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\User */

$this->title = "update ".$model->username;

?>
<div class="cliente-view">

    <div class="card card-container">
        <center><H1 id="idperfil">Perfil</H1></center>


        <?php $form = ActiveForm::begin(['id'=>'editarperfil']); ?>

        <?= $form->field($model, 'username')->textInput(['maxlength' => true]) ?>

        <?= $form->field($model, 'email')->textInput(['maxlength' => true]) ?>

        <?= $form->field($model, 'nif')->textInput() ?>

        <?= $form->field($model, 'numero')->textInput(['maxlength' => true]) ?>

        <div class="form-group">
            <center><?= Html::submitButton('Gravar', ['class' => 'btn btn-primary']) ?></center>
        </div>

        <?php ActiveForm::end(); ?>
    </div>
</div>
