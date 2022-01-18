<?php

namespace backend\models;

use Yii;

/**
 * This is the model class for table "consumo".
 *
 * @property int $id_consumo
 * @property int $id_pedido
 * @property int $id_product
 * @property int $quantidade
 * @property string $status
 *
 * @property Purchases $pedido
 * @property Products $product
 */
class Consumo extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'consumo';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['id_pedido', 'id_product', 'quantidade', 'status'], 'required'],
            [['id_pedido', 'id_product', 'quantidade'], 'integer'],
            [['status'], 'string'],
            [['id_pedido'], 'exist', 'skipOnError' => true, 'targetClass' => Purchases::className(), 'targetAttribute' => ['id_pedido' => 'id_purchase']],
            [['id_product'], 'exist', 'skipOnError' => true, 'targetClass' => Products::className(), 'targetAttribute' => ['id_product' => 'id_product']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_consumo' => 'Id Consumo',
            'id_pedido' => 'Id Pedido',
            'id_product' => 'Id Product',
            'quantidade' => 'Quantidade',
            'status' => 'Status',
        ];
    }

    /**
     * Gets query for [[Pedido]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getPedido()
    {
        return $this->hasOne(Purchases::className(), ['id_purchase' => 'id_pedido']);
    }

    /**
     * Gets query for [[Product]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getProduct()
    {
        return $this->hasOne(Products::className(), ['id_product' => 'id_product']);
    }
}
