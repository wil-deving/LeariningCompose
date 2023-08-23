package com.devwil.learningcompse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Text("Hello from Android") // Show simple Text
            // MessageCard("Williams")
            // showDistinctOrderCards()
            // simpleImageCard()
            // imageCardWithModifiers()
        }
    }
}

data class User(val name: String, val lastname: String)

// this function receives a param to show a text
@Composable
fun MessageCard(name: String) {
    Text("$name wants to learn Compose")
}

/*
 * Preview only to composable function and it does not receive any param
 * and it does not show in app compilation
 */
@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard("Roger")
}

@Preview
@Composable
fun UserCardShowChildrenAsColumn() {
    UserCardShowChildrenAsColumn(User("Roger", "Chavez"))
}

// order Text children vertically
@Composable
fun UserCardShowChildrenAsColumn(user: User) {
    Column {
        Text(text = "${user.name}")
        // add space between texts
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "${user.lastname}")
    }
}

// order Text children horizontally
@Composable
fun UserCardShowChildrenAsRow(user: User) {
    Row {
        Text(text = "${user.name}")
        Text(text = "${user.lastname}")
    }
}

// TODO Get more info
@Composable
fun UserCardShowChildrenAsBox(user: User) {
    Box {
        Text(text = "${user.name}")
        Text(text = "${user.lastname}")
    }
}

@Composable
fun showDistinctOrderCards() {
    Column {
        UserCardShowChildrenAsColumn(User("Williams", "Gutierrez"))
        UserCardShowChildrenAsRow(User("Williams", "Gutierrez"))
        //UserCardShowChildrenAsBox(User("Williams", "Gutierrez"))
    }
}

@Composable
fun simpleImageCard() {
    Row {
        Image (
            painter = painterResource(R.drawable.wil),
            contentDescription = "Will's photo"
        )
        UserCardShowChildrenAsColumn(User("Williams", "Gutierrez"))
    }
}

// Modifiers can give style improvements and interaction on components
@Composable
fun imageCardWithModifiers() {
    Row {
        Image (
            painter = painterResource(R.drawable.wil),
            contentDescription = "Will's photo",
            modifier = Modifier
                // Set image size to 40 dp
                .size(60.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
        )
        // Add an space between image and column
        Spacer(modifier = Modifier.width(8.dp))
        UserCardShowChildrenAsColumn(User("Williams", "Gutierrez"))
    }
}