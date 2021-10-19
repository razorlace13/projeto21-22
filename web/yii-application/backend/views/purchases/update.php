<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model backend\Models\Purchases */

$this->title = 'Update Purchases: ' . $model->id_purchase;
$this->params['breadcrumbs'][] = ['label' => 'Purchases', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_purchase, 'url' => ['view', 'id' => $model->id_purchase]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="purchases-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
