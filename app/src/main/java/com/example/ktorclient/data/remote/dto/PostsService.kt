package com.example.ktorclient.data.remote.dto

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


interface PostsService {
    suspend fun getPosts(): List<PostResponse>
    suspend fun createPost(postRequest: PostRequest): PostResponse?
    suspend fun getRandomRabbit(): Rabbit


    companion object {

        fun create(): PostsService {
            return PostsServiceImpl(
                client = HttpClient(Android)
                {
                    install(Logging)
                    {
                        level = LogLevel.ALL
                    }

                    install(ContentNegotiation) {
                        json(Json {
                            prettyPrint = true
                            isLenient = true
                        })
                    }
                }
            )
        }
    }
    }