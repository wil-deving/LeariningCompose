package com.devwil.learningcompse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.devwil.learningcompse.ui.theme.LearningCompseTheme

class CountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningCompseTheme {
                countHandler(0)
            }
        }
    }
}

@Composable
fun countHandler(counter: Int) {

    var counterState by remember { mutableStateOf(counter) }

    Row() {
        Button(onClick = {
            counterState++
        }) {
            Text(text = "+")
        }
        Text(text = "$counterState")
        Button(onClick = {
            counterState--
        }) {
            Text(text = "-")
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearningCompseTheme {
        Greeting("Android")
    }
}