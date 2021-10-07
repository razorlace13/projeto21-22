<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $searchModel app\models\PedidosSearch */
/* @var $form yii\widgets\ActiveForm */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Pedidos';
$this->params['breadcrumbs'][] = $this->title;
$session = Yii::$app->session;
?>

<div class="comments-index">
    <div class="comments-index">

        <H1 style="text-align: center ">Produtos do pedido <?= $session->get('teste'); ?></H1>
        <br>
        <HR style="height:150%;">
        <br>
        <ul style="text-align: center">
            <br><br>
            <h2>Produtos</h2>
            <?php
            $count = 1;
            foreach ($dataProvider as $model) {

                ?>

                <tr style="width: 100%;text-align: center">
                    <td style="text-align: center">
                        <h4 class="meta" style="text-align: center;margin: 10px 20% 10px;">
                            <?= $count ?>: <?= Html::encode($model->produto->designacao) ?></h4>
                        </td>
                </tr>
                <?php
                $count = $count + 1;
            }
            ?>

        </ul>

    </div>
</div>
