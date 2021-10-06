<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "user".
 *
 * @property int $id
 * @property string $username
 * @property string $email
 * @property string $password_hash
 * @property string $password_reset_token
 * @property string $auth_key
 * @property int|null $status
 * @property int $nif
 * @property int $numero
 * @property int $created_at
 * @property int $updated_at
 * @property string $verification_token
 *
 * @property Purchases[] $purchases
 */
class User extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'user';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['username', 'email', 'password_hash', 'password_reset_token', 'auth_key', 'nif', 'numero', 'created_at', 'updated_at', 'verification_token'], 'required'],
            [['status', 'nif', 'numero', 'created_at', 'updated_at'], 'integer'],
            [['username', 'email'], 'string', 'max' => 25],
            [['password_hash', 'password_reset_token', 'auth_key', 'verification_token'], 'string', 'max' => 255],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'username' => 'Username',
            'email' => 'Email',
            'password_hash' => 'Password Hash',
            'password_reset_token' => 'Password Reset Token',
            'auth_key' => 'Auth Key',
            'status' => 'Status',
            'nif' => 'Nif',
            'numero' => 'Numero',
            'created_at' => 'Created At',
            'updated_at' => 'Updated At',
            'verification_token' => 'Verification Token',
        ];
    }

    /**
     * Gets query for [[Purchases]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getPurchases()
    {
        return $this->hasMany(Purchases::className(), ['id_user' => 'id']);
    }
}
