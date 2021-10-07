<?php

/* @var $this yii\web\View */

use yii\helpers\Html;

$this->title = 'About - SnakRestaurant';
//$this->params['breadcrumbs'][] = $this->title;
?>
<div class="site-about">
    <h1><?= Html::encode($this->title) ?></h1>
    <div class="vk-about-welcometo">
        <div class="container">
            <div class="row">
                <div class="col-md-5">
                    <div class="vk-about-welcometo-left">
                        <?php echo Html::img(\Yii::$app->homeUrl.'img/about.png', ['width'=>'400','height'=>'500']);?>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="vk-about-welcometo-right">
                        <div class="vk-about-right-header">
                            <h3>Bem Vindo ao</h3>
                            <h2>SnakRestaurant</h2>
                            <div class="clearfix"></div>
                            <div class="vk-about-border"></div>
                        </div>
                        <div class="vk-about-right-content">
                            <p>O SnakRestaurant nasceu à 37 anos, 1976, foi fundado pelo senhor Manuel Henriques, pai da actual responsável pelo restaurante, Maria Luísa Henriques. </p>
                            <p>Inicialmente começou por ser uma pequena adega onde eram servidos pequenos petiscos acompanhados ao som do bom fado português. Com o decorrer do anos o restaurante foi crescendo e actualmente conta com 5 salas, cada sala têm uma decoração ligada à arte da tauromaquia.</p>
                            <p>Neste restaurante os nossos clientes são recebidos com enorme simpatia, e acabam assim por se sentir como se fossem da casa, envolvendo se neste ambiente acolhedor transmitido por esta equipa.</p>
                            <p><b>Fechamos à Quarta- Feira, excepto feriados.</b></p>
                            <div class="vk-about-right-content-border"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-12">
            <div class="vk-about-welcometo-share">
                <div class="vk-about-welcometo-share-border-left"></div>
                <div class="vk-about-welcometo-share-border-right">

                </div>
                <hr>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="col-md-7 vk-clear-padding">
            <div class="vk-contact-us-map">
                <div id="map"></div>
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d112109.48528044905!2d-9.117865908096366!3d39.043920745172585!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd18dbf362e8e773%3A0x4ae41305702a35e8!2sSnack%20Bar%20SCA%20%2F%20Raposo%20e%20Jesus%20Lda!5e0!3m2!1spt-PT!2spt!4v1633599149641!5m2!1spt-PT!2spt" width="1110" height="585" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
            </div>
        </div>
    </div>
</div>

