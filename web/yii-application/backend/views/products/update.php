<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model backend\Models\Products */

$this->title = 'Editar producto: ' . $model->name;
//$this->params['breadcrumbs'][] = ['label' => 'Products', 'url' => ['index']];
//$this->params['breadcrumbs'][] = ['label' => $model->name, 'url' => ['view', 'id_product' => $model->id_product]];
//$this->params['breadcrumbs'][] = 'Update';
?>
<div class="products-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
