<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel backend\Models\ConsumoSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Consumos';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="consumo-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Consumo', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php  echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id_consumo',
            'id_pedido',
            [
                'attribute' => 'id_product',
                'label' => 'nome do product',
                'value'     => 'product.name'
            ],
            'quantidade',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>


</div>
