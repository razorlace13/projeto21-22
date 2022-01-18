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
?>
<div class="products-create">


    <!--$form
        ->field($model, 'name', $fieldOptions1)
        ->label('name')
        ->textInput(['placeholder' => $model->getAttributeLabel('name')]) ?>
  -->


    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>


</div>
