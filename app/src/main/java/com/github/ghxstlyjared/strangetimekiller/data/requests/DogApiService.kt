package com.github.ghxstlyjared.strangetimekiller.data.requests

import com.github.ghxstlyjared.strangetimekiller.data.responses.RandomDogResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface DogApiService {
    @GET("breeds/image/random/3")
    fun getRandomDog(@QueryMap options: Map<String?, String?>?): Call<RandomDogResponse>
}