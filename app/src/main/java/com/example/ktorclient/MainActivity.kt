package com.example.ktorclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.DataSource
import com.example.ktorclient.data.remote.dto.HttpRoutesRabbits
import com.example.ktorclient.data.remote.dto.PostResponse
import com.example.ktorclient.data.remote.dto.PostsService
import com.example.ktorclient.ui.theme.KtorClientTheme

class MainActivity : ComponentActivity() {

    var vm = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val posts = produceState<List<PostResponse>>(initialValue = emptyList(), producer =
            { value = vm.service.getPosts() })

            KtorClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column() {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .weight(0.5f)
                                .background(
                                    Color.Red
                                )
                        )
                        {

                            Column() {

                                Button(onClick = { vm.getRandomRabbit() }) {

                                }
                                vm.state.value.rabbit?.let { Text(text = it.name) }
                                vm.state.value.rabbit?.let { Text(text = it.description) }
                                vm.state.value.rabbit?.let { Text(text = it.imageURL) }

                                val painter = rememberAsyncImagePainter(HttpRoutesRabbits.BASE_URL+vm.state.value.rabbit?.imageURL)

                                Image(
                                    painter = painter,
                                    contentDescription = "", contentScale = ContentScale.FillHeight
                                )
                            }

                        }

                        LazyColumn(
                            Modifier
                                .fillMaxHeight()
                                .weight(0.1f)
                        ) {
                            items(posts.value)
                            {
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Text(text = it.title)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = it.body)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

