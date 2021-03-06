<?php

namespace backend\tests\functional;

use backend\tests\FunctionalTester;
use common\fixtures\UserFixture;

/**
 * Class LoginCest
 */
class LoginCest
{
    /**
     * @var \backend\tests\FunctionalTester
     */
    protected $tester;
    public function _fixtures()
    {
        return [
            'user' => [
                'class' => UserFixture::className(),
                'dataFile' => codecept_data_dir() . 'login_data.php'
            ]
        ];
    }
    
    /**
     * @param FunctionalTester $I
     */
    public function loginUser(FunctionalTester $I)
    {
        $I->amOnPage('/site/login');
        $I->fillField('username', 'teste');
        $I->fillField('password', 'password');
        $I->click('login-button');
        $I->canSeeCurrentUrlMatches("http://backend.test/site/login");
        $I->dontSeeLink('Login');
        $I->dontSeeLink('Signup');
    }
}
