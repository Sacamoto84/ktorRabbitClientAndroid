package com.example.ktorclient.data.remote.dto

@kotlinx.serialization.Serializable
data class PostResponse(
    val body: String,
    val title: String,
    val id: Int,
    val userId: Int
)

@kotlinx.serialization.Serializable
data class PostRequest(
    val body: String,
    val title: String,
    val userId: Int
)

object HttpRoutes{
    private const val BASE_URL="https://jsonplaceholder.typicode.com"
    const val POSTS = "$BASE_URL/posts"
}

//───────────────────────────────────────────────────────────┐
// Кролики                                                   │
//───────────────────────────────────────────────────────────┤
@kotlinx.serialization.Serializable                        //│
data class Rabbit(                                         //│
    val name: String, //Имя кролика                        //│
    val description: String, //Описание                    //│
    val imageURL: String                                   //│
)                                                          //│
                                                           //│
object HttpRoutesRabbits{                                  //│
    const val BASE_URL="http://192.168.0.105:8080" //│
    const val RANDOMRABBIT = "${BASE_URL}/randomrabbit"    //│
}                                                          //│
//───────────────────────────────────────────────────────────┘