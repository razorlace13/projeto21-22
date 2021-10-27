<?php

namespace app\models;


use Bluerhinos\phpMQTT;
use Yii;

/**
 * This is the model class for table "products".
 *
 * @property int $id_product
 * @property string $name
 * @property int $price
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
            [['price', 'id_category'], 'integer'],
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
     * Gets query for [[Consumos]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getConsumos()
    {
        return $this->hasMany(Consumo::className(), ['id_product' => 'id_product']);
    }

    public function afterSave($insert, $changedAttributes)
    {
        parent::afterSave($insert, $changedAttributes);

        //Obter dados do registo em causa
        $id_product = $this->id_product;
        $name = $this->name;
        $price = $this->price;
        $id_category = $this->id_category;
        $myObj=new \stdClass();

        $myObj->id_product=$id_product;
        $myObj->name=$name;
        $myObj->price=$price;
        $myObj->id_category=$id_category;
        $myJSON = json_encode($myObj);
        if($insert)
            $this->FazPublish("INSERT",$myJSON);
        else
            $this->FazPublish("UPDATE",$myJSON);
    }

    public function afterDelete()
    {
        parent::afterDelete();
        $prod_id= $this->id_product;
        $myObj=new \stdClass();
        $myObj->id_product=$prod_id;
        $myJSON = json_encode($myObj);
        $this->FazPublish("DELETE",$myJSON);
    }
    public function FazPublish($canal,$msg)
    {
        $server = "127.0.0.1";
        $port = 1884;
        $username = ""; // set your username
        $password = ""; // set your password
        $client_id = "phpMQTT-publisher"; // unique!
        $mqtt = new phpMQTT($server, $port, $client_id);
        if ($mqtt->connect(true, NULL, $username, $password))
        {
            $mqtt->publish($canal, $msg, 0);
            $mqtt->close();
        }
        else { file_put_contents("debug.output","Time out!"); }
}

}
