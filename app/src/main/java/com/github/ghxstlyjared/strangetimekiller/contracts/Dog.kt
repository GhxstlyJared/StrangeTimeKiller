package com.github.ghxstlyjared.strangetimekiller.contracts

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Dog(
    @SerializedName("message") val urls: List<String>) : Serializable
