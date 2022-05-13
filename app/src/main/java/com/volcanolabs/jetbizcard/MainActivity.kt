package com.volcanolabs.jetbizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.volcanolabs.jetbizcard.ui.theme.JetbizcardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetbizcardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateBizCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .padding(12.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(
                corner = CornerSize(12.dp)
            ),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                Divider(
                    startIndent = 8.dp,
                    color = Color.LightGray,
                )
                CreateCardInfoSection()
                CreatePortfolioSection()
            }
        }
    }
}

@Composable
fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_user_icon),
            contentDescription = "Profile Image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun CreateCardInfoSection() {
    Column(
        modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Luis Lugo",
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.h4
        )
        Text(
            text = "Android Compose programmer",
            modifier = Modifier.padding(3.dp),
            color = Color.Gray,
        )
        Text(
            text = "@luislugodroid",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1,
            color = Color.Gray,
        )
    }
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(
                corner = CornerSize(6.dp)
            ),
            border = BorderStroke(
                width = 2.dp,
                color = Color.LightGray
            )
        ) {
            Portfolio(data = listOf("Project 1", "Project 2", "Project 3"))
        }
    }
}

@Composable
fun CreatePortfolioSection() {
    var buttonClickedState = remember {
        mutableStateOf(false)
    }
    Button(
        onClick = {
            Log.d("Clicked", "CreateBizCard: Clicked")
            buttonClickedState.value = !buttonClickedState.value
        },
        modifier = Modifier.padding(4.dp),
    ) {
        Text(
            text = "Portfolio",
            style = MaterialTheme.typography.button
        )
    }

    if (buttonClickedState.value) {
        Content()
    } else {
        Box {}
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface)
                        .padding(7.dp)
                ) {
                    CreateImageProfile(
                        modifier = Modifier.size(100.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(7.dp)
                            .align(alignment = Alignment.CenterVertically),
                    ) {
                        Text(
                            text = item,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "A great project",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }
}

// @Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetbizcardTheme {
        CreateBizCard()
    }
}