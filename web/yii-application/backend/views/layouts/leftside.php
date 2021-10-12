<?php

use adminlte\widgets\Menu;
use yii\helpers\Html;
use yii\helpers\Url;
?>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
<?= Html::img('@web/img/avatar04.png', ['class' => 'img-circle', 'alt' => 'User Image']) ?>
            </div>
            <div class="pull-left info">
                <a<i class="fa fa-circle text-success"></i> Online</a><br>
                <a><?= @Yii::$app->user->identity->username ?></a>
            </div>
        </div>
        <!-- search form
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
                </span>
            </div>
        </form>
        -->
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <?=
        Menu::widget(
                [
                    'options' => ['class' => 'sidebar-menu'],
                    'items' => [
                        ['label' => 'Menu', 'options' => ['class' => 'header']],
                        ['label' => 'Home',
                            'url' => ['/'], 'active' => $this->context->route == 'site/index'
                        ],
                        [
                            'label' => 'Admin',
                            'url' => '',
                            'items' => [
                                [
                                    'label' => 'Users',
                                    'url' => ['/user'],
                                    'icon' => 'fa fa-users',
				    'active' => $this->context->route == 'user/index'
                                ],
                                [
                                    'label' => 'Create permissions',
                                    'icon' => 'fa fa-database',
                                    'url' => '?r=master2/',
				    'active' => $this->context->route == 'master2/index'
                                ],
                                [
                                    'label' => 'grant permissions',
                                    'icon' => 'fa fa-database',
                                    'url' => '?r=master2/',
                                    'active' => $this->context->route == 'master2/index'
                                ]
                            ]
                        ],
                    ],
                ]
        )
        ?>
        
    </section>
    <!-- /.sidebar -->
</aside>
