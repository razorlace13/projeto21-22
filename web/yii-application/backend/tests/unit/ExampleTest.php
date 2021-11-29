<?php
namespace backend\tests;

use backend\models\Products;

class ExampleTest extends \Codeception\Test\Unit
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

        $model->setname('nome');
        $this->assertTrue($model->validate(['name']));

        $model->setprice('1');
        $this->assertTrue($model->validate(['price']));

        $model->setid_category('1');
        $this->assertTrue($model->validate(['id_category']));

        $model->save();

    }

}