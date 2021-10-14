<?php

use yii\bootstrap4\Modal;
use yii\helpers\Html;
use yii\grid\GridView;
use yii\helpers\Url;

/* @var $this yii\web\View */
/* @var $searchModel backend\Models\UserSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Users';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="user-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'username',
            'email:email',
            //'password_hash',
            //'password_reset_token',
            //'auth_key',
            'status',
            'nif',
            'numero',
            //'created_at',
            //'updated_at',
            //'verification_token',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

    <?php
    Modal::begin([
        'title' => '<h4>Adicionar utilizador</h4>',
        'id' => 'modal',
        'size' => 'modal-lg',

    ]);
    echo "<div id='modalContent'></div>";
    Modal::end();
    ?>


    <p>
        <?= Html::button('Adicionar Utilizador', ['value' => Url::to('http://backend.test/user/create') ,
                'class' => 'btn btn-success','id' => 'modalButton' ]
        ) ?>
    </p>

</div>
