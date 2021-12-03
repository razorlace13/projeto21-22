<?php

use yii\bootstrap4\ActiveForm;
use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model backend\Models\Products */

$this->title = 'Create Products';
$this->params['breadcrumbs'][] = ['label' => 'Products', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
$fieldOptions1 = [
    'options' => ['class' => 'form-group has-feedback'],
    'inputTemplate' => "{input}"
];
$fieldOptions2 = [
    'options' => ['class' => 'form-group has-feedback'],
    'inputTemplate' => "{input}"
];
$fieldOptions3 = [
    'options' => ['class' => 'form-group has-feedback'],
    'inputTemplate' => "{input}"
];
?>
<div class="products-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?php $form = ActiveForm::begin(['id' => 'products-form', 'enableClientValidation' => false]); ?>

    <?= $form
        ->field($model, 'name', $fieldOptions1)
        ->label('name')
        ->textInput(['placeholder' => $model->getAttributeLabel('name')]) ?>
    <?= $form
        ->field($model, 'price', $fieldOptions2)
        ->label('price')
        ->textInput(['placeholder' => $model->getAttributeLabel('price')]) ?>
    <?= $form
        ->field($model, 'id_category', $fieldOptions3)
        ->label('id_category')
        ->textInput(['placeholder' => $model->getAttributeLabel('id_category')]) ?>



    <div class="col-xs-4">
        <?= Html::submitButton('Submit', ['class' => 'btn btn-primary btn-block btn-flat', 'name' => 'product-button']) ?>
    </div>
    <?php ActiveForm::end(); ?>

</div>
