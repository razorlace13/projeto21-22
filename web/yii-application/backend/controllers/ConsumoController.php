<?php

namespace backend\controllers;

use backend\Models\Consumo;
use backend\Models\ConsumoSearch;
use Yii;
use yii\web\Controller;
use yii\web\ForbiddenHttpException;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * ConsumoController implements the CRUD actions for Consumo model.
 */
class ConsumoController extends Controller
{
    /**
     * @inheritDoc
     */
    public function behaviors()
    {
        return array_merge(
            parent::behaviors(),
            [
                'verbs' => [
                    'class' => VerbFilter::className(),
                    'actions' => [
                        'delete' => ['POST'],
                    ],
                ],
            ]
        );
    }

    /**
     * Lists all Consumo models.
     * @return mixed
     */
    public function actionIndex()
    {
        if (Yii::$app->user->can('empregado') || Yii::$app->user->can('admin')) {

            $searchModel = new ConsumoSearch();
        $dataProvider = $searchModel->search($this->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
        } else {
            throw new ForbiddenHttpException();

        }
    }

    /**
     * Displays a single Consumo model.
     * @param int $id_consumo Id Consumo
     * @return mixed
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionView($id)
    {
        if (Yii::$app->user->can('empregado') || Yii::$app->user->can('admin')) {

            return $this->render('view', [
            'model' => $this->findModel($id),
        ]);
        } else {
            throw new ForbiddenHttpException();

        }
    }

    /**
     * Creates a new Consumo model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        if (Yii::$app->user->can('empregado') || Yii::$app->user->can('admin')) {

            $model = new Consumo();

        if ($this->request->isPost) {
            if ($model->load($this->request->post()) && $model->save()) {
                return $this->redirect(['view', 'id' => $model->id_consumo]);
            }
        } else {
            $model->loadDefaultValues();
        }

        return $this->render('create', [
            'model' => $model,
        ]);
        } else {
            throw new ForbiddenHttpException();

        }
    }

    /**
     * Updates an existing Consumo model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param int $id_consumo Id Consumo
     * @return mixed
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id)
    {
        if (Yii::$app->user->can('empregado') || Yii::$app->user->can('admin')) {

            $model = $this->findModel($id);

        if ($this->request->isPost && $model->load($this->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id_consumo]);
        }

        return $this->render('update', [
            'model' => $model,
        ]);
        } else {
            throw new ForbiddenHttpException();

        }
    }

    /**
     * Deletes an existing Consumo model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param int $id_consumo Id Consumo
     * @return mixed
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionDelete($id)
    {
        if (Yii::$app->user->can('empregado') || Yii::$app->user->can('admin')) {

            $this->findModel($id)->delete();

        return $this->redirect(['index']);
        } else {
            throw new ForbiddenHttpException();

        }
    }

    /**
     * Finds the Consumo model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id_consumo Id Consumo
     * @return Consumo the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id_consumo)
    {
        if (($model = Consumo::findOne($id_consumo)) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
