package com.devwil.learningcompse

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.material3.Button
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Text("Hello from Android") // Show simple Text
            // MessageCard("Williams")
            // showDistinctOrderCards()
            // simpleImageCard()
            // imageCardWithModifiers()
            // imageCardWithMaterialUiTheme()
            // ConversationWithDefaultMessages(SampleMessagesData.conversationSample)
            // ConversationWithAnimation(SampleMessagesData.conversationSample)

            activityPassButton()
        }
    }
}

data class User(val name: String, val lastname: String)
data class Message(val userName: String, val message: String)

@Composable
fun activityPassButton() {
    val context = LocalContext.current
    Button(onClick = {
        Toast.makeText(context, "By pass Activity", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, CountActivity::class.java)
        startActivity(context, intent, null)
    }) {
        Text(text = "Go to counter view")
    }
}

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

// this functions compose components in different orders
@Composable
fun showDistinctOrderCards() {
    Column {
        UserCardShowChildrenAsColumn(User("Williams", "Gutierrez"))
        UserCardShowChildrenAsRow(User("Williams", "Gutierrez"))
        //UserCardShowChildrenAsBox(User("Williams", "Gutierrez"))
    }
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
        Text(text = "${user.name}",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall
        )
        // add space between texts
        Spacer(modifier = Modifier.height(4.dp))

        Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
            Text(
                text = "${user.lastname}",
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
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

// Simple Card with an image from resources
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

// Modifiers can give style, improvements and interaction on components
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

// Image Card with theme properties and managed by Material UI
/**
 * MaterialTheme Main pillars
 * colorScheme, typography, shapes
 */
@Composable
fun imageCardWithMaterialUiTheme() {
    Row {
        Image (
            painter = painterResource(R.drawable.wil),
            contentDescription = "Will's photo",
            modifier = Modifier
                // Set image size to 40 dp
                .size(60.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        // Add an space between image and column
        Spacer(modifier = Modifier.width(8.dp))
        UserCardShowChildrenAsColumn(User("Williams", "Gutierrez"))
    }
}

/**
 * This function compose a list of messages as a chat with default messages
 * that are extended completely
 */
@Composable
fun ConversationWithDefaultMessages(messages: List<Message>) {
    // use lazyColumn to render items according user interactions
    LazyColumn {
        items(messages) { message ->
            messageTextCard(
                User("Williams", "Gutierrez"),
                message
            )
        }
    }
}

@Composable
fun messageTextCard(user: User, message: Message) {
    Row {
        Image (
            painter = painterResource(R.drawable.wil),
            contentDescription = "Will's photo",
            modifier = Modifier
                // Set image size to 40 dp
                .size(60.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        // Add an space between image and column
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "${user.name}",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            // add space between texts
            Spacer(modifier = Modifier.height(4.dp))
            // surface shape gives an elevation to card message
            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
                Text(
                    text = "${message.message}",
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

/**
 * This function shows a chat with messages that save an state
 * to expand or collapse each message
 */
@Composable
fun ConversationWithAnimation(messages: List<Message>) {
    // use lazyColumn to render items according user interactions
    LazyColumn {
        items(messages) { message ->
            messageTextCardWithAnimation(
                User("Williams", "Gutierrez"),
                message
            )
        }
    }
}

@Composable
fun messageTextCardWithAnimation(user: User, message: Message) {
    Row {
        Image (
            painter = painterResource(R.drawable.wil),
            contentDescription = "Will's photo",
            modifier = Modifier
                // Set image size to 40 dp
                .size(60.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        // Add an space between image and column
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }

        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )

        // We toggle the isExpanded variable when we click on this Column
        Column (modifier = Modifier.clickable { isExpanded = !isExpanded }) {// click action

            Text(text = "${user.name}",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            // add space between texts
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = "${message.message}",
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1, // control lines of each message
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


// TODO
// show composable but it does not work
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCardTEST() {
    Surface {
        showDistinctOrderCards()
    }
}