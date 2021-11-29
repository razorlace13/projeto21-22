<?php

namespace backend\models;

use yii\base\Model;
use yii\data\ActiveDataProvider;
use backend\models\Products;

/**
 * ProductsSearch represents the model behind the search form of `app\models\Products`.
 */
class ProductsSearch extends Products
{
    /**
     * @var mixed|null
     */
    public $globalSearch;

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['price', 'id_category'], 'integer'],
            [['name'], 'safe'],
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
        //$query = Products::find()->joinWith(['category']);
        $query = Products::find();

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $dataProvider->sort->attributes['price'] = [
            'asc' => ['price' =>SORT_ASC],
            'desc' => ['price' =>SORT_DESC]
        ];

        $dataProvider->sort->attributes['id category'] = [
            'asc' => ['category.id_category' =>SORT_ASC],
            'desc' => ['category.id_category' =>SORT_DESC]
        ];

        $dataProvider->sort->attributes['nome'] = [
            'asc' => ['name' =>SORT_ASC],
            'desc' => ['name' =>SORT_DESC]
        ];


        $this->load($params);

        if (!$this->validate()) {

            return $dataProvider;
        }

        $query->orFilterWhere(['like','name',$this->globalSearch])
              ->orFilterWhere(['like','price',$this->globalSearch])
              ->orFilterWhere(['like','id_category',$this->globalSearch]);

        /*  // grid filtering conditions
        $query->andFilterWhere([
            'id_product' => $this->id_product,
            'price' => $this->price,
            'id_category' => $this->id_category,
        ]);

        //pesquisa pela primeira letra
        $query->orFilterWhere(['like','category.id_category', $this->globalSearch]) //o valor do id_utilizador era integer
        // para que conseguise procurar por
        // letrar tive que mudar o id_utilizador
        // para string la em cima no rules
        ->orFilterWhere(['like','name',$this->globalSearch])
            ->orFilterWhere(['like','price',$this->globalSearch]);
*/

        return $dataProvider;
    }
}
