package com.ekineskn.chatgpt.repository

import com.ekineskn.chatgpt.models.Answer
import com.ekineskn.chatgpt.models.BaseModel
import com.ekineskn.chatgpt.models.Message
import kotlinx.coroutines.flow.Flow

interface AppRepo {
    suspend fun askQuestion(previousQuestions:List<Message>,question:String):BaseModel<Answer>
    suspend fun getMessages(): Flow<List<com.ekineskn.chatgpt.models.Message>>
    suspend fun addAnswer(answer: Message)
}