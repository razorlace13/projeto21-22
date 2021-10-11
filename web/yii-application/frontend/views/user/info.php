<?php
//use app\models\Cliente;
use yii\helpers\Html;
use yii\web\View;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model User\models\User */

//$this->title = $model->username;

?>
<div class="cliente-view">


    <div class="card" style="">
        <div class="card-body" style="padding-left: 30%;padding-right: 30%">
            <H1 id="idperfil" style="text-align: center">Perfil</H1>
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
