<?php
namespace backend\tests\functional;
use backend\tests\FunctionalTester;
class testeCest
{
    public function _before(FunctionalTester $I)
    {
    }

    /**
     * @param FunctionalTester $I
     */
    public function test1(FunctionalTester $I)
    {
        $I-> amOnPage('/site/login');
        $I->see("Backoffice apenas para pessoas autorizadas");
        $I->fillField('username', 'teste');
        $I->fillField('password', 'password');
        $I->click('login-button');
        $I->amOnPage('/products/create');
        $I->seeCurrentUrlEquals('/products/create');
        $I->fillField('Nome', 'teste');
        $I->fillField('Preço', '2');

    }

    /**
     * @param FunctionalTester $I
     */
    public function test2(FunctionalTester $I)
    {
        $I-> amOnPage('/site/login');
        $I->see("Backoffice apenas para pessoas autorizadas");
        $I->fillField('username', 'teste');
        $I->fillField('password', 'password');
        $I->click('login-button');
        $I->amOnPage('/category/create');
        $I->seeCurrentUrlEquals('/category/create');
        $I->fillField('Category[name]', 'prato');
    }
}
