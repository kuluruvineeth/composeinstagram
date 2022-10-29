package com.kuluruvineeth.composeinstagram.presentation.Main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kuluruvineeth.composeinstagram.ComposeInstagramApp
import com.kuluruvineeth.composeinstagram.presentation.BottomNavigationItem
import com.kuluruvineeth.composeinstagram.presentation.BottomNavigationMenu
import com.kuluruvineeth.composeinstagram.presentation.Main.Posts.PostViewModel
import com.kuluruvineeth.composeinstagram.presentation.Toast
import com.kuluruvineeth.composeinstagram.util.Response
import com.kuluruvineeth.composeinstagram.R
import com.kuluruvineeth.composeinstagram.domain.model.Post

@Composable
fun FeedScreen(
    navController: NavController
) {
    val postViewModel : PostViewModel = hiltViewModel()
    postViewModel.getAllPosts()
    when(val response = postViewModel.postData.value){
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            val obj = response.data
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "ComposeInstagram")
                        },
                        backgroundColor = MaterialTheme.colors.surface,
                        elevation = 10.dp,
                        navigationIcon = {
                            IconButton(onClick = { }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.compose_instagram_logo),
                                    contentDescription = "Logo"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.compose_instagram_logo),
                                    contentDescription = "Logo",
                                    modifier = Modifier.size(30.dp),
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    )
                },
                content = {
                    ComposeInstagramContent(obj)
                },
                bottomBar = {
                    BottomNavigationMenu(
                        selectedItem = BottomNavigationItem.FEED,
                        navController = navController
                    )
                }
            )




        }
        is Response.Error -> {
            Toast(message = response.message)
        }
    }
}

@Composable
fun ComposeInstagramContent(postsList: List<Post>) {
    LazyColumn{
        items(postsList.size){
            InstagramListContent(postsList[it])
        }
    }
}

@Composable
fun InstagramListContent(it:Post) {
    Column {
        ProfileInfoSection(it)
        PostImage(it)
        PostDescription(it)
    }
}

@Composable
fun PostDescription(it: Post) {
    Text(
        text = it.postDescription,
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(30.dp),
    )
}

@Composable
fun PostImage(it: Post) {
    Icon(
        rememberImagePainter(data = it.postImage),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth().height(450.dp)
    )

}

@Composable
fun ProfileInfoSection(it:Post) {
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            rememberImagePainter(data = it.userImage),
            contentDescription = "ProfileImage",
            tint = Color.Unspecified
        )
        Text(
            text = it.userName,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            textAlign = TextAlign.Left
        )
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "null"
        )
    }
}