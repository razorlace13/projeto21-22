<?php
namespace backend\tests;

use backend\models\Products;

class ProductsTest extends \Codeception\Test\Unit
{
    /**
     * @var \backend\tests\UnitTester
     */
    protected $tester;
    
    protected function _before()
    {
    }

    protected function _after()
    {
    }

    // tests
    public function testSomeFeature()
    {

        $model = new Products();

        $model->setname(null);
        $this->assertFalse($model->validate(['name']));

        $model->setprice(null);
        $this->assertFalse($model->validate(['price']));

        $model->setid_category(null);
        $this->assertFalse($model->validate(['id_category']));

        $model->setname('lorem ipsum dolor sit amet consectetur adipiscing elit vulputate aliquam sagittis metus nisl ridiculus euismod ornare tincidunt nec interdum lobortis aptent imperdiet bibendum dapibus sed sociosqu nostra dui penatibus venenatis phasellus non quam faucibus rutrum vel magnis quisque arcu lacus eu luctus porta diam tempor facilisis vitae ultricies semper cras maximus consequat fermentum vivamus tristique iaculis ante commodo felis suspendisse fames dis integer ligula taciti habitasse mus nulla elementum pellentesque feugiat senectus inceptos curabitur lectus laoreet ex ultrices himenaeos vestibulum libero primis facilisi mi mollis turpis est erat pretium velit orci purus aenean sollicitudin odio mauris duis congue nam netus1');
        $this->assertFalse($model->validate(['name']));

        $model->setprice('letra');
        $this->assertFalse($model->validate(['price']));

        $model->setid_category(0);
        $this->assertFalse($model->validate(['id_category']));


        $model->setname('nome');
        $this->assertTrue($model->validate(['name']));

        $model->setprice(1);
        $this->assertTrue($model->validate(['price']));

        // se for verdadeiro pode-se trocar o id para o id que estiver na bd
        // o mesmo serve para os outros id's

        $model->setid_category(1);
        $this->assertTrue($model->validate(['id_category']));

        $this->assertTrue($model->save());

        // 
       // $model = Products::findOne(['name' => 'nome', 'price' => 1, 'id_category' => 1]);


    }


}