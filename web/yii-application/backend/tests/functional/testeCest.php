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
        $I->see("Backoffice only for authorized persons");
        $I->fillField('username', 'teste');
        $I->fillField('password', 'password');
        $I->click('login-button');
        $I->amOnPage('/products/create');
        $I->seeCurrentUrlEquals('/products/create');
        $I->fillField('name', 'teste');
        $I->fillField('price', '2');
        $I->fillField('id_category', '1');
        $I->click('product-button');

    }

    /**
     * @param FunctionalTester $I
     */
    public function test2(FunctionalTester $I)
    {
        $I-> amOnPage('/site/login');
        $I->see("Backoffice only for authorized persons");
        $I->fillField('username', 'teste');
        $I->fillField('password', 'password');
        $I->click('login-button');
        $I->amOnPage('/category/create');
        $I->seeCurrentUrlEquals('/category/create');
        $I->fillField('Category[name]', 'prato');
    }
}
