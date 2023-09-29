package com.ekineskn.chatgpt.network

import com.ekineskn.chatgpt.models.Answer
import com.ekineskn.chatgpt.models.Question
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val APIKEY = "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"

interface Api {
    @POST("completions")
    @Headers(
        "Authorization: Bearer $APIKEY",
        "Content-Type: application/json"
    )
    suspend fun askQuestion(
        @Body question: Question
    ):Response<Answer>
}