<?php

namespace backend\models;

use Yii;

/**
 * This is the model class for table "products".
 *
 * @property int $id_product
 * @property string $name
 * @property double $price
 * @property int $id_category
 *
 * @property Category $category
 * @property Consumo[] $consumos
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
            [['id_category'], 'integer'],
            [['price'], 'double'],
            [['name'], 'string', 'max' => 11],
            [['id_category'], 'exist', 'skipOnError' => true, 'targetClass' => Category::className(), 'targetAttribute' => ['id_category' => 'id_category']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_product' => 'Chave do producto',
            'name' => 'Nome',
            'price' => 'Preço',
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
     * Gets query for [[Consumos]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getConsumos()
    {
        return $this->hasMany(Consumo::className(), ['id_product' => 'id_product']);
    }

// nomes para os testes
    public function setname($name)
    {
        $this->name=$name;
    }
    public function setprice($price)
    {
        $this->price=$price;
    }

    public function setid_category($id_category)
    {
        $this->id_category=$id_category;
    }



}
