<?php
namespace backend\tests\functional;
use backend\tests\FunctionalTester;
class MyNewScenarioCest
{
    public function _before(FunctionalTester $I)
    {
    }

    /**
     * @param FunctionalTester $I
     */
    public function tryToTest(FunctionalTester $I)
    {
        $I-> amOnPage('/site/login');
        $I->see("username");
        $I->see("Backoffice only for authorized persons");
        $I->fillField('username', 'teste');
        $I->fillField('password', 'password');
        $I->click('login-button');
        sleep(2);
        $I->amOnPage('/products/create');
        $I->see("Id Product");
        $I->canSeeCurrentUrlMatches("http://backend.test/");
        $I->amOnPage('/products/create');
        $I->click('button_test');

    }
}
