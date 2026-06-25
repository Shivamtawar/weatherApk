package com.example.firebaseintegration.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginPage(
    navController: NavController
) {

    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current



    var email by remember {
        mutableStateOf("")
    }

    var pass by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)

    ) {
        OutlinedTextField(
            value = email,
            onValueChange = {email=it},
            label = { Text("please enter email") }
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )


        OutlinedTextField(
            value = pass,
            onValueChange = {pass=it},
            label = { Text("password") },
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Button(
            onClick = {
                auth.signInWithEmailAndPassword(email,pass)
                    .addOnSuccessListener {


                        navController.navigate("home")
                        Toast.makeText(
                            context,
                            "Login is done",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        ) {
            Text("login")
        }

        Button(
            onClick = {
                navController.navigate("signup")
            }
        ) {
            Text("take me to signup page")
        }
    }
}