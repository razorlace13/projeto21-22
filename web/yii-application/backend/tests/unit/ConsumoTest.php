<?php
namespace backend\tests;

use backend\models\Consumo;

class ConsumoTest extends \Codeception\Test\Unit
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
        $model = new Consumo();

        $model->setid_pedido(0);
        $this->assertFalse($model->validate(['id_pedido']));

        // se for verdadeiro pode-se trocar o id para o id que estiver na bd
        // o mesmo serve para os outros id's

        $model->setid_pedido(61);
        $this->assertTrue($model->validate(['id_pedido']));

        $model->setid_product(0);
        $this->assertFalse($model->validate(['id_product']));

        $model->setid_product(1);
        $this->assertTrue($model->validate(['id_product']));

        $model->setquantidade('ee');
        $this->assertFalse($model->validate(['quantidade']));

        $model->setquantidade(1);
        $this->assertTrue($model->validate(['quantidade']));

        $model->save();
    }
}