<?php

use backend\assets\AppAsset;
use Codeception\PHPUnit\ResultPrinter\HTML;
use yii\widgets\Breadcrumbs;
use yii\widgets\AlertLte;

AppAsset::register($this);
?>
	  <!-- Content Wrapper. Contains page content -->
	  <div class=" content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header ">


			<?=
			Breadcrumbs::widget([
				'links' => isset($this->params['breadcrumbs']) ? $this->params['breadcrumbs'] : [],
			]) ?>
        </section>

        <!-- Main content -->
        <section class="content">
			<?= $content ?>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
