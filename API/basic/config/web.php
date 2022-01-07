<?php

$params = require __DIR__ . '/params.php';
$db = require __DIR__ . '/db.php';

$config = [
    'id' => 'basic',
    'basePath' => dirname(__DIR__),
    'bootstrap' => ['log'],
    'aliases' => [
        '@bower' => '@vendor/bower-asset',
        '@npm'   => '@vendor/npm-asset',
    ],
    'modules' => [
        'v1' => [
            'class' => 'app\modules\v1\Module',
        ],
    ],
    'components' => [
        'request' => [
            // !!! insert a secret key in the following (if it is empty) - this is required by cookie validation
            'cookieValidationKey' => 'uQzeukx1q02J2uK4W5ExRQjYLCw9Gawl',
            'parsers' => [
                'application/json' => 'yii\web\JsonParser',
            ]

        ],
        'cache' => [
            'class' => 'yii\caching\FileCache',
        ],
        'user' => [
            'identityClass' => 'app\models\User',
            'enableAutoLogin' => true,
            'enableSession' =>false,
            'loginUrl' => null,
            'identityCookie' => ['name' => '_identity','httpOnly' => true],
        ],
        'errorHandler' => [
            'errorAction' => 'site/error',
        ],
        'mailer' => [
            'class' => 'yii\swiftmailer\Mailer',
            // send all mails to a file by default. You have to set
            // 'useFileTransport' to false and configure a transport
            // for the mailer to send real emails.
            'useFileTransport' => true,
        ],
        'log' => [
            'traceLevel' => YII_DEBUG ? 3 : 0,
            'targets' => [
                [
                    'class' => 'yii\log\FileTarget',
                    'levels' => ['error', 'warning'],
                ],
            ],
        ],
        'db' => $db,

        'urlManager' => [
            'enablePrettyUrl' => true,
            'showScriptName' => false,
            'rules' => [
                ['class' => 'yii\rest\UrlRule',
                    'controller' => 'v1/products',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET total' => 'total' ,
                        'GET {id}/name' => 'name',
                        'GET find_id_category/{limit}' => 'find_id_category',
                        'GET set/{limit}' => 'set',
                        'POST post' => 'post',
                        'PUT put/{id_product}'=>'put',
                        'DELETE  delete/{id_product}' => 'delete'
                    ],
                    'tokens' => [ '{id_product}'    => '<id:\d+>', '{limit}' => '<limit:\d+>', ],
                ],
                ['class' => 'yii\rest\UrlRule',
                    'controller' => 'v1/login',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET get/{username}&{password}' => 'get',
                    ],
                    'tokens' => [ '{id_product}'    => '<id:\d+>', '{limit}' => '<limit:\d+>', ],
                ],
                ['class' => 'yii\rest\UrlRule',
                    'controller' => 'v1/category',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET total' => 'total' ,
                        'GET {id}/name' => 'name',
                        'GET set/{limit}' => 'set',
                        'POST post' => 'post',
                        'PUT put/{id_category}'=>'put',
                        'DELETE  delete/{id_category}' => 'delete'
                    ],
                    'tokens' => [ '{id_category}'    => '<id:\d+>', '{limit}' => '<limit:\d+>', ],
                ],

                ['class' => 'yii\rest\UrlRule',
                    'controller' => 'v1/purchases',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET total' => 'total' ,
                        'GET purchasesuser/{id_user}' => 'purchasesuser',
                        'GET set/{limit}' => 'set',
                        'GET consumo/{id_user}' => 'consumo',

                        'POST post' => 'post',
                        'PUT put/{id_purchase}'=>'put',
                        'DELETE  delete/{id_purchase}' => 'delete'
                    ],
                    'tokens' => ['{id_purchase}'    => '<id_purchase:\d+>', '{limit}' => '<limit:\d+>','{id_user}'    => '<id:\d+>' ,],
                ],

                ['class' => 'yii\rest\UrlRule',
                    'controller' => 'v1/user',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET total' => 'total' ,
                        'GET set/{limit}' => 'set',
                        'GET {id}/nif' => 'nif',
                        'GET {id}/username' => 'username',
                        'GET {acess}/token' => 'token',

                        'POST post' => 'post',
                        'PUT put/{id}'=>'put',
                        'PUT putsomefields/{id}'=>'putsomefields',
                        'DELETE  delete/{id}' => 'delete'
                    ],
                    'tokens' => [ '{id}'    => '<id:\d+>', '{limit}' => '<limit:\d+>',"{acess}" => "<acess>", ],
                ],
                ['class' => 'yii\rest\UrlRule',
                    'controller' => 'v1/consumo',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET consumopedido/{id_pedido}' => 'consumopedido',
                        'GET total' => 'total' ,
                        'GET set/{limit}' => 'set',
                        'POST post' => 'post',
                        'PUT put/{id_consumo}'=>'put',
                        'DELETE  delete/{id_consumo}' => 'delete'
                    ],
                    'tokens' => [ '{id_consumo}'    => '<id:\d+>','{id_pedido}'    => '<id:\d+>', '{limit}' => '<limit:\d+>' ],
                ],
                ['class' => 'yii\rest\UrlRule',
                    'controller' => 'v1/signup',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'POST post' => 'post',
                    ],
                    'tokens' => ['{id}'    => '<id:\d+>' ],
                ],
            ],
],
    ],
    'params' => $params,
];

if (YII_ENV_DEV) {
    // configuration adjustments for 'dev' environment
    $config['bootstrap'][] = 'debug';
    $config['modules']['debug'] = [
        'class' => 'yii\debug\Module',
        // uncomment the following to add your IP if you are not connecting from localhost.
        //'allowedIPs' => ['127.0.0.1', '::1'],
    ];

    $config['bootstrap'][] = 'gii';
    $config['modules']['gii'] = [
        'class' => 'yii\gii\Module',
        'allowedIPs' => ['*'],
        // uncomment the following to add your IP if you are not connecting from localhost.
        //'allowedIPs' => ['127.0.0.1', '::1'],
    ];
}

return $config;
