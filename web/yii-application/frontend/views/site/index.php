<?php
use yii\helpers\Html;
/* @var $this yii\web\View */

$this->title = 'SnakRestaurant';

$date = date('Y');
$date2 = 1976;
$data_final = $date - $date2;
?>
<style>
    table, th, td {
        border: 1px solid black;
        text-align: center  ;
    }
</style>
<div class="site-index">

    <div style="text-align: center">
        <h1>SnakRestaurant</h1>
        <?php echo Html::img(\Yii::$app->homeUrl.'img/index.jpg', ['width'=>'1080','height'=>'400']);?>
        <br>
        <br>
        <br>
        <p style="font-family:Serif  ;font-size: large;">O SnakRestaurant nasceu à <?php echo $data_final?> anos, 1976, foi fundado pelo senhor Manuel Henriques, pai da actual
            responsável pelo restaurante, Maria Luísa Henriques. <br>
            Inicialmente começou por ser uma pequena adega onde eram servidos pequenos petiscos acompanhados ao som do bom fado português. Com o decorrer do
            anos o restaurante foi crescendo e actualmente conta com 5 salas, cada sala têm uma decoração ligada
            à arte da tauromaquia.<br>
            Neste restaurante os nossos clientes são recebidos com enorme simpatia, e acabam assim por se sentir
            como se fossem da casa, envolvendo se neste ambiente acolhedor transmitido por esta equipa.</p>
    </div>
    <br>
    <br>
    <div style="text-align: center">
        <table style="width:100%">
            <tr>
                <th>Segunda-feira</th>
                <th>Terça-feira</th>
                <th>Quarta-feira</th>
                <th>Quinta-feira</th>
                <th>Sexta-feira</th>
                <th>Sabado</th>
                <th>Domingo</th>
            </tr>
            <tr>
                <td>Aberto</td>
                <td>Aberto</td>
                <td>Fechado</td>
                <td>Aberto</td>
                <td>Aberto</td>
                <td>Aberto</td>
                <td>Aberto</td>
            </tr>
        </table>
    </div>

    <br>
    <br>

    <div style="text-align: center">
        <table style="width:100%">
            <tr>
                <th>Facebook</th>
                <th>tripadvisor</th>
                <th>Intervalo de preços</th>
                <th>Telefone</th>
                <th>email</th>
            </tr>
            <tr>
                <td><a href="https://www.facebook.com/">Facebook</a></td>
                <th><a href="https://www.tripadvisor.pt/">Tripadvisor</a></th>
                <th>$$</th>
                <td>262 888 111</td>
                <td>SnakRestaurant@hotmail.com</td>
            </tr>
        </table>
    </div>



</div>
