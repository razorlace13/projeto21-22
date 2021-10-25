<?php

namespace backend\controllers;

use backend\Models\Purchases;
use backend\Models\PurchasesSearch;
use Yii;
use yii\web\Controller;
use yii\web\ForbiddenHttpException;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * PurchasesController implements the CRUD actions for Purchases model.
 */
class PurchasesController extends Controller
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
     * Lists all Purchases models.
     * @return mixed
     */
    public function actionIndex()
    {
        if (Yii::$app->user->can('empregado') || Yii::$app->user->can('admin')) {

            $searchModel = new PurchasesSearch();
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
     * Displays a single Purchases model.
     * @param int $id_purchase Id Purchase
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
     * Creates a new Purchases model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        if (Yii::$app->user->can('empregado') || Yii::$app->user->can('admin')) {

            $model = new Purchases();

        if ($this->request->isPost) {
            if ($model->load($this->request->post()) && $model->save()) {
                return $this->redirect(['view', 'id' => $model->id_purchase]);
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
     * Updates an existing Purchases model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param int $id_purchase Id Purchase
     * @return mixed
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id)
    {
        if (Yii::$app->user->can('empregado') || Yii::$app->user->can('admin')) {

            $model = $this->findModel($id);

        if ($this->request->isPost && $model->load($this->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id_purchase]);
        }

        return $this->render('update', [
            'model' => $model,
        ]);
        } else {
            throw new ForbiddenHttpException();

        }
    }

    /**
     * Deletes an existing Purchases model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param int $id_purchase Id Purchase
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
     * Finds the Purchases model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id_purchase Id Purchase
     * @return Purchases the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id_purchase)
    {
        if (($model = Purchases::findOne($id_purchase)) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
