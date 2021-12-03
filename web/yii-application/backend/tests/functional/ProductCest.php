<?php namespace backend\tests\functional;
use backend\tests\FunctionalTester;
use common\fixtures\UserFixture;

class ProductCest
{

    protected $tester;
    /**
     * @var \backend\tests\FunctionalTester
     */
    public function _fixtures()
    {
        return [
            'user' => [
                'class' => UserFixture::className(),
                'dataFile' => codecept_data_dir() . 'login_data.php'
            ]
        ];
    }
    public function _before(FunctionalTester $I)
    {
    }

    // tests
    public function createBCest(FunctionalTester $I)
    {
        $I->amOnPage('/site/login');
        $I->fillField('username', 'teste');
        $I->fillField('password', 'password');
        $I->click('login-button');


        $I->amOnPage('/products/create');
        //$I->fillField('name','teste');
        $I->fillField('price','12');
        $I->fillField('id_category','1');
        $I->click('Save');
        
        

    }
}
