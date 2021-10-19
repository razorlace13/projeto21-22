<?php

namespace backend\Models;

use yii\base\Model;
use yii\data\ActiveDataProvider;
use backend\Models\Purchases;

/**
 * PurchasesSearch represents the model behind the search form of `backend\Models\Purchases`.
 */
class PurchasesSearch extends Purchases
{
    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['id_purchase', 'id_product', 'id_user'], 'integer'],
            [['valor'], 'number'],
            [['data'], 'safe'],
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
        $query = Purchases::find();

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
            'id_purchase' => $this->id_purchase,
            'valor' => $this->valor,
            'data' => $this->data,
            'id_product' => $this->id_product,
            'id_user' => $this->id_user,
        ]);

        return $dataProvider;
    }
}
