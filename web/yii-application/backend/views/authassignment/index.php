<?php

use yii\helpers\Html;
use yii\grid\GridView;
use yii\widgets\Pjax;

/* @var $this yii\web\View */
/* @var $searchModel backend\Models\AuthassignmentSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Conceder permissões';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="authassignment-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <?php Pjax::begin(['enablePushState' => false]); ?>
    <?php  echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'item_name',
            //como mudar o valor que aparede
            [
                'attribute' => 'user_id',//o valor da tabela em questao
                'label' => 'Utilizador',//o titulo que queremos que apareça
                'value'     => 'user.username'// e o valor pelo qual queremos que ele troque
            ],
            'created_at',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>



</div>
