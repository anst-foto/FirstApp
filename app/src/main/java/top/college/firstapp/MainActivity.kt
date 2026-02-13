package top.college.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import top.college.firstapp.ui.theme.FirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FirstAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    List(label = "Имя", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Input(label: String, inputText: MutableState<String>, modifier: Modifier) {
    Row(modifier = modifier) {
        Text(
            text = label,
            modifier = modifier,
            color = colorResource(R.color.purple_700)
        )
        TextField(
            value = inputText.value,
            onValueChange = {text -> inputText.value = text},
            modifier = modifier
        )
    }
}

@Composable
fun List(label: String, modifier: Modifier = Modifier) {
    val buffer = remember{mutableStateOf("")}
    val message = remember{mutableStateOf("")}
    val messages = remember { mutableStateListOf<String>() }

    Column(modifier = modifier) {
        LazyColumn(modifier = modifier.fillMaxWidth()) {
            item { Row() { Text(text = "TEXT") } }
            items(messages){msg -> Text(text = msg)}
        }

        Text(
            text = message.value,
            modifier = modifier,
            color = colorResource(R.color.teal_700)
        )

        Input(label = "Имя", inputText = buffer, modifier = modifier)

        Button(
            content = {Text(text = "Сохранить")},
            modifier = modifier,
            onClick = {
                message.value = buffer.value
                messages.add(buffer.value)
            })
    }
}