package com.example.gamegenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamegenerator.entities.Game
import com.example.gamegenerator.entities.GameList
import com.example.gamegenerator.ui.theme.GameGeneratorTheme
import com.example.gamegenerator.utils.Cache

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameGeneratorTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    view()
                }
            }
        }
    }
}

@Composable
fun view(){
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor =  MaterialTheme.colors.background,
                title = {
                    Text(text="Gerador de Jogo",
                        color = Color.Gray
                    )
                }
            )
        },
        bottomBar = {
            buttonSave(onClick = {
                println(Cache.getInstance().getGameInstance().getList())
            })
        },

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val instance = Cache.getInstance()
            instance.getGameInstance().generate()

            list(instance.getGameInstance().getList())
        }
    }
}

@Composable
fun list(item: List<Game>) {
    LazyColumn{
        items(items = item){item->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(all = 5.dp),
                elevation = 6.dp,
                shape = RoundedCornerShape(size = 8.dp)
            ) {
                itemLazy(item = item)
            }
        }
    }
}

@Composable
fun itemLazy(item: Game){
    Column(modifier = Modifier
        .fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = item.name,
                color = Color.Gray,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp)
            checkboxGame(item)
        }
    }
}

@Composable
fun checkboxGame(game: Game) {
    var checked  = remember { mutableStateOf(game.select) }

    Checkbox(
        checked = checked.value,
        onCheckedChange = {
            val instance = Cache.getInstance()
            instance.getGameInstance().updateList(game)

            checked.value = it },
        colors = CheckboxDefaults.colors(
            uncheckedColor = Color.Gray,
            checkedColor = Color.Gray
        ),
    )
}

@Composable
fun buttonSave(onClick: () -> Unit){
    OutlinedButton(
        modifier = Modifier
            .padding(vertical = 2.dp, horizontal = 120.dp).fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        border = BorderStroke(1.dp, Color.Gray),
        onClick = { onClick() }
    ) {
        Text(text = "Gerar", color = Color.Gray)
    }
}

@Composable
fun textFieldKm() {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        label = { Text("Quilômetro") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
    )
}

@Composable
fun textFieldDate() {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        label = { Text("Data") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    GameGeneratorTheme {
        view()
    }
}