<?php

namespace backend\Models;

use yii\base\Model;
use yii\data\ActiveDataProvider;
use backend\Models\Consumo;

/**
 * ConsumoSearch represents the model behind the search form of `backend\Models\Consumo`.
 */
class ConsumoSearch extends Consumo
{
    /**
     * {@inheritdoc}
     */
    public $globalSearch;

    public function rules()
    {
        return [
            [['id_consumo', 'id_pedido', 'id_product', 'quantidade'], 'integer'],
            [['globalSearch'], 'safe'],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     *
     * @return ActiveDataProvider
     */
    public function search($params)
    {
        $query = Consumo::find();

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        // grid filtering conditions
        $query->andFilterWhere([
            'id_consumo' => $this->id_consumo,
            'id_pedido' => $this->id_pedido,
            'id_product' => $this->id_product,
            'quantidade' => $this->quantidade,
        ]);

        $query->orFilterWhere(['like','id_consumo',$this->globalSearch])
            ->orFilterWhere(['like','id_pedido',$this->globalSearch])
            ->orFilterWhere(['like','id_product',$this->globalSearch])
            ->orFilterWhere(['like','quantidade',$this->globalSearch]);

        return $dataProvider;
    }
}
