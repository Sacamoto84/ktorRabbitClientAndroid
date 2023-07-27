package com.example.ktorclient.data.remote.dto

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostsServiceImpl(
    private val client: HttpClient
) : PostsService {




    override suspend fun getPosts(): List<PostResponse> {
        return try {
            val response: List<PostResponse> = client.get(HttpRoutes.POSTS).body()
            println(response)
            response

        } catch (e: RedirectResponseException) {
            // 3xx - response
            println("Error : ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - response
            println("Error : ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - response
            println("Error : ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error : ${e.message}")
            emptyList()
        }

    }

    //Получить ответ кроликов
    override suspend fun getRandomRabbit(): Rabbit {
        return try {
            val response: Rabbit = client.get(HttpRoutesRabbits.RANDOMRABBIT).body()
            println(response)
            response

        } catch (e: RedirectResponseException) {
            // 3xx - response
            println("Error : ${e.response.status.description}")
            Rabbit("","","")
        } catch (e: ClientRequestException) {
            // 4xx - response
            println("Error : ${e.response.status.description}")
            Rabbit("","","")
        } catch (e: ServerResponseException) {
            // 5xx - response
            println("Error : ${e.response.status.description}")
            Rabbit("","","")
        } catch (e: Exception) {
            println("Error : ${e.message}")
            Rabbit("","","")
        }

    }



    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {

            // client.post<PostResponse>{
            //     url(HttpRoutes.POSTS)
            //     contentType(ContentType.Application.Json)
            //     body = postRequest
            // }
            null

        } catch (e: RedirectResponseException) {
            // 3xx - response
            println("Error : ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - response
            println("Error : ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - response
            println("Error : ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error : ${e.message}")
            null
        }
    }





}