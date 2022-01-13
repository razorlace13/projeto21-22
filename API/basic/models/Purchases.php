<?php

namespace app\models;

use Bluerhinos\phpMQTT;
use Yii;

/**
 * This is the model class for table "purchases".
 *
 * @property int $id_purchase
 * @property float $valor
 * @property string $data
 * @property int $mesa
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
            [['valor', 'data', 'mesa', 'id_user'], 'required'],
            [['valor'], 'number'],
            [['data'], 'safe'],
            [['mesa', 'id_user'], 'integer'],
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
            'mesa' => 'Mesa',
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


    public function afterSave($insert, $changedAttributes)
    {
        parent::afterSave($insert, $changedAttributes);

        //Obter dados do registo em causa
        $id_purchase = $this->id_purchase;
        $valor = $this->valor;
        $data = $this->data;
        $mesa = $this->mesa;
        $myObj=new \stdClass();

        $myObj->id_purchase=$id_purchase;
        $myObj->valor=$valor;
        $myObj->mesa=$mesa;
        $myJSON = json_encode($myObj);
        if($insert)
            $this->FazPublish("INSERT",$myJSON);
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
