<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "products".
 *
 * @property int $id_product
 * @property int $name
 * @property int $price
 * @property int $id_category
 *
 * @property Category $category
 * @property Purchases[] $purchases
 */
class Products extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'products';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['name', 'price', 'id_category'], 'required'],
            [['name', 'price', 'id_category'], 'integer'],
            [['id_category'], 'exist', 'skipOnError' => true, 'targetClass' => Category::className(), 'targetAttribute' => ['id_category' => 'id_category']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_product' => 'Id Product',
            'name' => 'Name',
            'price' => 'Price',
            'id_category' => 'Id Category',
        ];
    }

    /**
     * Gets query for [[Category]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getCategory()
    {
        return $this->hasOne(Category::className(), ['id_category' => 'id_category']);
    }

    /**
     * Gets query for [[Purchases]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getPurchases()
    {
        return $this->hasMany(Purchases::className(), ['id_product' => 'id_product']);
    }
}
