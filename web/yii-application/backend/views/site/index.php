<?php

/* @var $this yii\web\View */

use backend\assets\AppAsset;
use yii\bootstrap4\Html;

$this->title = 'My Yii Application';
AppAsset::register($this);
?>

<div class="site-index">
    <div style="background: url(https://images.wallpaperscraft.com/image/single/coffee_mug_chair_124443_1920x1080.jpg)" class="page-holder bg-cover">
        <header class="text-center text-white py-5">
            <h1 class="display-4 font-weight-bold mb-4">Home Page</h1>

        </header>

        <div class="body-content center">
            <?= Html::a('User', ['user/indexb '], ['class' => 'button-1']) ?>

            <?= Html::a('label', ['/controller/action'], ['class' => 'button-2']) ?>
        </div>
        <div class="body-content center">
            <?= Html::a('label', ['/controller/action'], ['class' => 'button-2']) ?>

            <?= Html::a('label', ['/controller/action'], ['class' => 'button-1']) ?>
        </div>
    </div>
</div>
