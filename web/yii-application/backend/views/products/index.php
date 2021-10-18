<?php

use yii\bootstrap4\Modal;
use yii\helpers\Html;
use yii\grid\GridView;
use yii\helpers\Url;
use yii\widgets\Pjax;

/* @var $this yii\web\View */
/* @var $searchModel backend\models\ProductsSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Products';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="products-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php Pjax::begin(['enablePushState' => false]); ?>
    <?php  echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id_product',
            'name',
            'price',
            [
                'attribute' => 'id_category',//o valor da tabela em questao
                'label' => 'Categoria',//o titulo que queremos que apareça
                'value'     => 'category.id_category'// e o valor pelo qual queremos que ele troque
            ],

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
    <?php
    Modal::begin([
        'title' => '<h4>Adicionar Pedido</h4>',
        'id' => 'modal',
        'size' => 'modal-lg',

    ]);
    echo "<div id='modalContent'></div>";
    Modal::end();
    ?><?php Pjax::end(); ?>
    <p> <?= Html::button('Adicionar pedido', ['value' => Url::to('http://backend.test/pedidos/create') ,
                'class' => 'btn btn-success','id' => 'modalButton' ]
        ) ?>


    </p>


</div>
