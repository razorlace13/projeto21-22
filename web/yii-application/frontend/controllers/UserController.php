<?php

namespace frontend\controllers;

use app\models\Consumo;
use app\models\Purchases;
use app\models\UserSearch;
use Yii;
use app\models\User;
use yii\web\Controller;
use yii\web\ForbiddenHttpException;
use yii\web\NotFoundHttpException;


/**
 * UserController implements the CRUD actions for User model.
 */
class UserController extends Controller
{

    public function actionIndex()
    {
        $searchModel = new UserSearch();
        $dataProvider = $searchModel->search($this->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    public function actionPedidos($id)
    {
        $searchModel = Purchases::find()
            ->where(['id_user' => $id])
            ->all()
        ;
        $dataProvider = $searchModel;
        return $this->render('pedidos', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    public function actionConsumo($id, $numero)
    {
        $session = Yii::$app->session;
        $session->set('teste', $numero);
        $searchModel = Consumo::find()
            ->joinWith('product','')
            ->where(['consumo.id_pedido' => $id])
            ->all()
        ;
        $dataProvider = $searchModel;
        return $this->render('consumo', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    public function actionInfo()
    {

        $nomeuser = Yii::$app->user->getIdentity();

        if(Yii::$app->user->can("utilizador" )|| Yii::$app->user->can('empregado')|| Yii::$app->user->can('admin')) {
            return $this->render('info',
                [
                    'nomeuser' =>  $nomeuser
                ]);
        }
        else{
            throw new ForbiddenHttpException();

        }
    }

    /**
     * Displays a single User model.
     * @param integer $id
     * @return mixed
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionView($id)
    {
        return $this->render('view', [
            'model' => $this->findModel($id),
        ]);
    }

    /**
     * Updates an existing User model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param integer $id
     * @return mixed
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id)
    {
        $model = $this->findModel($id);
        Yii::debug($model->username);
        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        }

        return $this->render('update', [
            'model' => $model,
        ]);
    }

    /**
     * Deletes an existing User model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param integer $id
     * @return mixed
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionDelete($id)
    {
        $this->findModel($id)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the User model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param integer $id
     * @return User the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = User::findOne($id)) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
