package com.example.app_nativa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.appnativa.ui.theme.AppNativaTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Product(val name: String, var isChecked: Boolean = false)
data class Category(val name: String, val products: List<Product>)

class Products : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNativaTheme {
                ProductsScreen(listName = "" ,modifier = Modifier)
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
fun ProductsScreen(listName: String, modifier: Modifier = Modifier) {
    var categories by remember { mutableStateOf(listOf(
        Category("Categoría 1", listOf(
            Product("Producto 1"),
            Product("Producto 2"),
            Product("Producto 3")
        )),
        Category("Categoría 2", listOf(
            Product("Producto 1"),
            Product("Producto 2")
        ))
    )) }

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text(listName,Modifier.padding(10.dp), color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Handle back navigation */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF89E4C4))
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Handle adding new product */ },
                containerColor = Color(0xFF89E4C4)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Product", tint = Color.Black)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
        ) {
            item {

                Row(modifier = Modifier
                    .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically){
                    Icon(Icons.Default.Add, contentDescription = "Add Product", tint = Color.White)
                    Text(
                        "Agregar producto",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }

            }

            items(categories) { category ->
                CategoryItem(category) { updatedCategory ->
                    categories = categories.map { if (it.name == updatedCategory.name) updatedCategory else it }
                }
            }
        }
    }
}
@Composable
fun CategoryItem(category: Category, onCategoryUpdated: (Category) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()
        .padding(start = 30.dp, end = 30.dp)) {
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
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                category.name,
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
            category.products.forEach { product ->
                ProductItem(product) { isChecked ->
                    val updatedProducts = category.products.map {
                        if (it.name == product.name) it.copy(isChecked = isChecked) else it
                    }
                    onCategoryUpdated(category.copy(products = updatedProducts))
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = product.isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF89E4C4),
                uncheckedColor = Color.White,
                checkmarkColor = Color.Black
            )
        )
        Text(
            product.name,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Productos")
@Composable
fun ProductsPreview() {
    AppNativaTheme {
        ProductsScreen(listName = "Nueva")
    }
}