package com.example.appnativa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appnativa.ui.theme.AppNativaTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class ChangePassword : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNativaTheme {
                ChangePasswordScreen(modifier = Modifier)
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
fun ChangePasswordScreen(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nombre_App", color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Handle back navigation */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF89E4C4))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(150.dp))  // Incrementa el espacio inicial

            // Título principal
            Text(
                text = "Cambiar Contraseña",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(48.dp))  // Espacio entre título y campos

            // Campo de usuario
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario", color = Color(0xFF89E4C4)) },
                modifier = Modifier
                    .fillMaxWidth(0.8f),  // Reduce el ancho al 80% de la pantalla
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color(0xFF89E4C4),
                    unfocusedBorderColor = Color(0xFF89E4C4)
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Campo de contraseña actual
            OutlinedTextField(
                value = currentPassword,
                onValueChange = { currentPassword = it },
                label = { Text("Contraseña actual", color = Color(0xFF89E4C4)) },
                modifier = Modifier
                    .fillMaxWidth(0.8f),  // Reduce el ancho al 80% de la pantalla
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color(0xFF89E4C4),
                    unfocusedBorderColor = Color(0xFF89E4C4)
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Campo de nueva contraseña
            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("Nueva contraseña", color = Color(0xFF89E4C4)) },
                modifier = Modifier
                    .fillMaxWidth(0.8f),  // Reduce el ancho al 80% de la pantalla
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color(0xFF89E4C4),
                    unfocusedBorderColor = Color(0xFF89E4C4)
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Campo de confirmar nueva contraseña
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirmar nueva contraseña", color = Color(0xFF89E4C4)) },
                modifier = Modifier
                    .fillMaxWidth(0.8f),  // Reduce el ancho al 80% de la pantalla
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color(0xFF89E4C4),
                    unfocusedBorderColor = Color(0xFF89E4C4)
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón para cambiar contraseña
            Button(
                onClick = { /* TODO: Handle password change */ },
                modifier = Modifier.fillMaxWidth(0.8f),  // Reduce el ancho al 80% de la pantalla
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF89E4C4))
            ) {
                Text("Actualizar", color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordPreview() {
    AppNativaTheme {
        ChangePasswordScreen()
    }
}
