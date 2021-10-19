<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model backend\Models\Consumo */

$this->title = 'Create Consumo';
$this->params['breadcrumbs'][] = ['label' => 'Consumos', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="consumo-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
