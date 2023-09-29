package com.ekineskn.chatgpt.repository

import com.ekineskn.chatgpt.database.AnswerDao
import com.ekineskn.chatgpt.database.AnswerEntity
import com.ekineskn.chatgpt.models.Answer
import com.ekineskn.chatgpt.models.BaseModel
import com.ekineskn.chatgpt.models.Message
import com.ekineskn.chatgpt.models.Question
import com.ekineskn.chatgpt.network.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepoImpl(
    private val api: Api,
    private val dao: AnswerDao
):AppRepo {
    override suspend fun askQuestion(
        previousQuestions: List<Message>,
        question: String
    ): BaseModel<Answer> {
        try {
            api.askQuestion(
                question = Question(
                    messages = previousQuestions + Message(
                        role = "user",
                        content = question
                    )
                )
            ).also {
                return if (it.isSuccessful){
                    BaseModel.Success(data = it.body()!!)
                }else{
                    BaseModel.Error(it.errorBody()?.string().toString())
                }
            }
        }catch (e:Exception){
            return BaseModel.Error(e.message.toString())
        }
    }

    override suspend fun getMessages(): Flow<List<Message>> {
        return dao.getAnswers().map {
            it.map {
                Message(it.role,it.content)
            }
        }
    }

    override suspend fun addAnswer(answer: Message) {
        dao.addAnswer(
            AnswerEntity(
            role = answer.role,
            content = answer.content
        )
        )
    }

}