package com.ekineskn.chatgpt.network

import com.ekineskn.chatgpt.models.Answer
import com.ekineskn.chatgpt.models.Question
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val APIKEY = xxx  /*"Here your api key"*/

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
