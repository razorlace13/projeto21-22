<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $searchModel app\models\PedidosSearch */
/* @var $form yii\widgets\ActiveForm */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Pedidos';
$this->params['breadcrumbs'][] = $this->title;
?>

<div class="comments-index">
    <div class="comments-index">

        <H1 style="text-align: center ">Pedidos</H1>
        <br>
        <HR style="height:150%;">
        <br>
        <ul style="text-align: center">
            <br><br>
            <?php
            $total = sizeof($dataProvider);
            $count = $total;
            foreach (array_reverse($dataProvider) as $model) {

                ?>
                <tr style="width: 100%;text-align: center">
                    <td style="text-align: center">
                        <h2><?= $count ?> pedido</h2>
                        <h4 class="meta" style="text-align: center;margin: 10px 20% 10px;">
                            Mesa: <?= Html::encode($model->mesa->numero) ?></h4>
                        <h4 class="meta" style="text-align: center;margin: 10px 20% 10px;">
                            Valor: <?= Html::encode($model->valor) ?></h4>
                        <h4 class="meta" style="text-align: center;margin: 10px 20% 10px;">
                            Data: <?= Html::encode($model->data) ?></h4>
                        <?= Html::a('Ver Consumo', ['consumo', 'id' => $model->id, 'numero' => $count], ['name' => 'edita', 'id' => 'edita', 'class' => 'btn btn-primary']) ?>
                    </td>
                </tr>
                <hr>
                <?php
                $count = $count - 1;
            }
            ?>

        </ul>

    </div>
</div>
