package com.malgudi.app.productsection.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ReviewModel(
        @SerializedName("data")
        val `data`: Data?,
        @SerializedName("success")
        val success: Boolean?
) : Serializable