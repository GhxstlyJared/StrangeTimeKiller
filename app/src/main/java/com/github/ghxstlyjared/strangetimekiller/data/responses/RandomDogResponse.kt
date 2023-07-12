package com.github.ghxstlyjared.strangetimekiller.data.responses

import com.github.ghxstlyjared.strangetimekiller.contracts.Dog
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import java.io.Serializable

data class RandomDogResponse(
    @SerializedName("message") val urls: List<String>) : Serializable