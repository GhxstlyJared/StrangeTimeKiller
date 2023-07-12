package com.github.ghxstlyjared.strangetimekiller.mvp.presenters

import android.util.Log
import com.github.ghxstlyjared.strangetimekiller.contracts.MainActivityContract
import com.github.ghxstlyjared.strangetimekiller.data.responses.RandomDogResponse
import com.github.ghxstlyjared.strangetimekiller.mvp.models.DogModel
import retrofit2.Response


class MainViewPresenter(view: MainActivityContract.View) : MainActivityContract.Presenter,
    MainActivityContract.APIListener {

    var mView: MainActivityContract.View? = null
    var mModel: MainActivityContract.Model? = null

    init {
        mView = view
        mModel = DogModel()
        mView!!.setupUI()
        getDog(mView!!.aPIKey)
    }




    override fun getDog(apiKey: String) {
        mView?.showProgressDialog();
        mModel?.getDog(apiKey, this); }

    override fun onSuccess(response: RandomDogResponse?) {
        Log.d("mvp", response.toString())
        mView?.hideProgressDialog();
        mView?.displayDogData(response)
    }

    override fun onError(response: Response<RandomDogResponse>) {
        mView?.hideProgressDialog()
        mView?.showMessage("Error Occured.")
    }

    override fun onFailure(t: Throwable?) {
        mView?.hideProgressDialog()
        mView?.showMessage(t?.message)
    }
}