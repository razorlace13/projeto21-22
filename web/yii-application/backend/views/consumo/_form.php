<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model backend\Models\Consumo */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="consumo-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'id_pedido')->textInput() ?>

    <?= $form->field($model, 'id_product')->textInput() ?>

    <?= $form->field($model, 'quantidade')->textInput() ?>

    <?= $form->field($model, 'status')->dropDownList
    (array('1'=>'Pedido Pago','2'=>'Pedido Cancelado','3'=>'Pedido em Execução','4'=>'Pedido Entregue'),
        ['promp'=>'select status'])?>

    <div class="form-group">
        <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
