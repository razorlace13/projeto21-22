<?php
//use app\models\Cliente;
use yii\helpers\Html;
use yii\web\View;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model User\models\User */

//$this->title = $model->username;

?>

<h4>Perfil</h4>
<div class="cliente-view">

        <div class="body" style="padding-left: 25%;padding-right: 25%">
            <?= DetailView::widget([
                'model' => $nomeuser,
                'attributes' => [
                    'username',
                    'email',
                    'nif',
                    'numero'

                ],
            ]) ?>
            <div style="text-align: center">
                <?= Html::a('Editar', ['update', 'id' => $nomeuser->id], ['name' => 'edita', 'id' => 'edita', 'class' => 'btn btn-primary']) ?>
                <?= Html::a('Pedidos', ['pedidos', 'id' => $nomeuser->id], ['name' => 'edita', 'id' => 'edita', 'class' => 'btn btn-primary']) ?>
            </div>
        </div>
    </div>
</div>
