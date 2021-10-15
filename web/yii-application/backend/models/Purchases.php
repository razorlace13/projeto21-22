<?php

namespace backend\models;

use Yii;

/**
 * This is the model class for table "purchases".
 *
 * @property int $id_purchase
 * @property float $valor
 * @property string $data
 * @property int $id_user
 *
 * @property Consumo[] $consumos
 * @property User $user
 */
class Purchases extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'purchases';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['valor', 'data', 'id_user'], 'required'],
            [['valor'], 'number'],
            [['data'], 'safe'],
            [['id_user'], 'integer'],
            [['id_user'], 'exist', 'skipOnError' => true, 'targetClass' => User::className(), 'targetAttribute' => ['id_user' => 'id']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_purchase' => 'Id Purchase',
            'valor' => 'Valor',
            'data' => 'Data',
            'id_user' => 'Id User',
        ];
    }

    /**
     * Gets query for [[Consumos]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getConsumos()
    {
        return $this->hasMany(Consumo::className(), ['id_pedido' => 'id_purchase']);
    }

    /**
     * Gets query for [[User]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getUser()
    {
        return $this->hasOne(User::className(), ['id' => 'id_user']);
    }
}
