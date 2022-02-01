<?php

namespace app\models;

use Yii;
use yii\base\Model;
use app\models\User;

/**
 * This is the model class for table "user".
 *
 * @property int $id
 * @property string $username
 * @property string $email
 * @property string $password_hash
 * @property string $password_reset_token
 * @property string $auth_key
 * @property int $status
 * @property int $nif
 * @property int $numero
 * @property string $created_at
 * @property string $updated_at
 * @property string $verification_token
 *
 * @property AuthAssignment[] $authAssignments
 * @property AuthItem[] $itemNames
 * @property Purchases[] $purchases
 */
class SignupForm extends Model
{



    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [

            ['username', 'trim'],
            ['username', 'required'],
            ['username', 'unique',  'message' => 'This username has already been taken.'],
            ['username', 'string', 'min' => 2, 'max' => 255],

            ['numero', 'required'],
            ['numero','string','length'=>9],
            ['nif', 'required'],
            ['nif','string','length'=>9],


            ['email', 'trim'],
            ['email', 'required'],
            ['email', 'email'],
            ['email', 'string', 'max' => 255],
            ['email', 'unique', 'targetClass' => '\common\models\User', 'message' => 'This email address has already been taken.'],

            ['password', 'required'],
            ['password', 'string'],

        ];
    }

    /**
     * Signs user up.
     *
     * @return bool whether the creating new account was successful and email was sent
     */
    public function signup()
    {
        if ($this->validate()) {

            $user = new User();

            $user->username = $this->username;
            $user->email = $this->email;
            $user->numero = $this->numero;
            $user->nif = $this->nif;
            $user->status = 10;
            $user->setPassword($this->password);
            $user->generateAuthKey();
            $user->generateEmailVerificationToken();
            $user->created_at = date('d-m-y');
            $user->save();

            //permissões
            $permission="utilizador";
            $newpermission = new AuthAssignment();
            $newpermission->user_id = $user->id;
            $newpermission->item_name = $permission;
            $newpermission->created_at = date('y-m-d');
            $newpermission->save();

            return $user;
        }
        return null;
    }


}

