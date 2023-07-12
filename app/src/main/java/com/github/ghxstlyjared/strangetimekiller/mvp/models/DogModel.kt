package com.github.ghxstlyjared.strangetimekiller.mvp.models

import android.util.Log
import com.github.ghxstlyjared.strangetimekiller.contracts.Dog
import com.github.ghxstlyjared.strangetimekiller.contracts.MainActivityContract
import com.github.ghxstlyjared.strangetimekiller.data.requests.DogApiService
import com.github.ghxstlyjared.strangetimekiller.data.responses.RandomDogResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DogModel : MainActivityContract.Model{
    override fun getDog(apiKey: String, listener: MainActivityContract.APIListener?) {

        try {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: DogApiService = retrofit.create(DogApiService::class.java)

            val options: HashMap<String?, String?> = HashMap()
            options["api_key"] = apiKey
            options["language"] = "en-US"

            val call: Call<RandomDogResponse> = service.getRandomDog(options)
            call.enqueue(object : Callback<RandomDogResponse> {
                override fun onResponse(
                    call: Call<RandomDogResponse>,
                    response: Response<RandomDogResponse>
                ) {
                    if (response.isSuccessful) {
                        listener!!.onSuccess(response.body() as RandomDogResponse?)
                    } else {
                        listener!!.onError(response)
                    }
                }

                override fun onFailure(call: Call<RandomDogResponse>, t: Throwable) {
                    listener!!.onFailure(t)
                }
            })
        }
        catch (e : Exception) {
            e.printStackTrace()
            Log.d("DogModelTAG", e.message.toString())

        }
    }
}