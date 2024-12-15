package com.example.helloworldkotlinjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.helloworldkotlinjetpackcompose.ui.theme.HelloWorldKotlinJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkMode by remember { mutableStateOf(false) }

            Switch(checked = isDarkMode, { isDarkMode = it }, Modifier.zIndex(1f).padding(24.dp))
            HelloWorldKotlinJetpackComposeTheme(darkTheme = isDarkMode) {
                Greeting(isDarkMode)
            }
        }
    }
}

@Composable
fun Greeting(isDarkMode: Boolean) {

    var greeting by remember { mutableStateOf("Hello") }
    var name by remember { mutableStateOf("World") }
    val backgroundColor = when(isDarkMode) {
        true -> Brush.verticalGradient(colors = listOf(Color.Cyan, Color.DarkGray))
        else -> Brush.verticalGradient(colors = listOf(Color.LightGray, Color.Cyan))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 24.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        TextField(
            value = greeting,
            onValueChange = { greeting = it },
            label = { Text("Enter Greeting") },
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter Name") },
        )
        var fullText = if (greeting.isNotEmpty()) "$greeting, " else ""
        fullText += if (name.isNotEmpty()) "$name!" else ""
        val textColor by animateColorAsState(targetValue = if (isDarkMode) Color.White else Color.Blue)
        Text(
            text = fullText,
            color = textColor, style = MaterialTheme.typography.headlineLarge
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            TextButton(onClick = {
                greeting = "Hello"
                name = "World"
            }) {
                Text("DEFAULT")
            }
            TextButton(onClick = {
                greeting = ""
                name = ""
            }) {
                Text("CLEAR")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldKotlinJetpackComposeTheme {
        Greeting(false)
    }
}