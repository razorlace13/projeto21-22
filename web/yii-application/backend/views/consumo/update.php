<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model backend\Models\Consumo */

$this->title = 'Editar dados do consumo: ' . $model->id_consumo;
$this->params['breadcrumbs'][] = ['label' => 'Consumos', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_consumo, 'url' => ['view', 'id_consumo' => $model->id_consumo]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="consumo-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
