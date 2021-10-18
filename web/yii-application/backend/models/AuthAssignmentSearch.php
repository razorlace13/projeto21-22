<?php

namespace backend\Models;

use yii\base\Model;
use yii\data\ActiveDataProvider;
use backend\Models\Authassignment;

/**
 * AuthassignmentSearch represents the model behind the search form of `backend\Models\Authassignment`.
 */
class AuthassignmentSearch extends Authassignment
{
    public $globalSearch;

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['item_name', 'created_at'], 'safe'],
            [['user_id'], 'integer'],
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
        $query = Authassignment::find()->joinWith(['user']);

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);
        //aqui da para ver como adicionar a ordenção tanto para acs tanto para desc para todos os campos
        $dataProvider->sort->attributes['id_utilizador'] = [
            'asc' => ['user.username' =>SORT_ASC],
            'desc' => ['user.username' =>SORT_DESC]
        ];

        $dataProvider->sort->attributes['data'] = [
            'asc' => ['created_at' =>SORT_ASC],
            'desc' => ['created_at' =>SORT_DESC]
        ];

        $dataProvider->sort->attributes['valor'] = [
            'asc' => ['item_name' =>SORT_ASC],
            'desc' => ['item_name' =>SORT_DESC]
        ];

        //acaba aqui

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        // grid filtering conditions
        $query->andFilterWhere([
            'user_id' => $this->user_id,
            'created_at' => $this->created_at,
        ]);

        $query->orFilterWhere(['like','user.username', $this->globalSearch]) //o valor do id_utilizador era integer
                                                                             // para que conseguise procurar por
                                                                             // letrar tive que mudar o id_utilizador
                                                                             // para string la em cima no rules
              ->orFilterWhere(['like', 'item_name', $this->globalSearch]);

        return $dataProvider;
    }
}
