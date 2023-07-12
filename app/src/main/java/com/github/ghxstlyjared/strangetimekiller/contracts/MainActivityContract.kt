package com.github.ghxstlyjared.strangetimekiller.contracts

import com.github.ghxstlyjared.strangetimekiller.data.responses.RandomDogResponse
import retrofit2.Response


interface MainActivityContract {

    interface Model {
        fun getDog(apiKey: String, listener: APIListener?) // Retrieve random dog
    }

    interface View {
        fun setupUI()

        // This method give out api key from string resource mentioned as part or string.xml file in res/values folder.
        val aPIKey: String

        fun displayDogData(moviesList: RandomDogResponse?)
        fun showMessage(msg: String?) // To display message as Toast messages

        // Show and hide progress dialog
        fun showProgressDialog()
        fun hideProgressDialog()
    }

    interface Presenter {
        fun getDog(apiKey: String)
    }

    interface APIListener {
        fun onSuccess(response: RandomDogResponse?)
        fun onError(response: Response<RandomDogResponse>)
        fun onFailure(t: Throwable?)
    }
}