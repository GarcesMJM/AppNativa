package com.example.appnativa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app_nativa.ProductsScreen
import com.example.appnativa.ui.theme.AppNativaTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Lists : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNativaTheme {
                ListsScreen(appName = "" ,modifier = Modifier)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListsScreen(appName: String, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(appName, color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Handle back navigation */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF89E4C4))
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Text(
                    "Listas",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            item {
                NewListButton()
            }

            item {
                ListCategory("En proceso", listOf("Lista 1", "Lista 2", "Lista 3"))
            }

            item {
                ListCategory("Completadas", listOf("Lista 1", "Lista 2"))
            }
        }
    }
}

@Composable
fun NewListButton() {
    Button(
        onClick = { /* TODO: Handle new list creation */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF89E4C4))
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "Create New List",
            tint = Color.Black,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Nueva Lista", color = Color.Black)
    }
}

@Composable
fun ListCategory(title: String, lists: List<String>) {
    var expanded by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    tint = Color.White
                )
            }
        }

        if (expanded) {
            lists.forEach { list ->
                ListItem(list)
            }
        }
    }
}

@Composable
fun ListItem(listName: String) {

    Column (
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()       // Que ocupe todo el ancho disponible
                .height(2.dp)         // Grosor de la línea
                .background(Color.White) // Color de la línea
                .padding(bottom = 20.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Checkbox(
                checked = false,
                onCheckedChange = { /* TODO: Handle list completion */ },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF89E4C4),
                    uncheckedColor = Color.White,
                    checkmarkColor = Color.Black
                )
            )
            Text(
                listName,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }


}

@Preview(showBackground = true, showSystemUi = true, name = "Listas")
@Composable
fun ListsPreview() {
    AppNativaTheme {
        ListsScreen(appName = "")
    }
}