package com.example.firebaseintegration.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firebaseintegration.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun SignUp(
    navController: NavController
) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val  db = FirebaseDatabase.getInstance()



    var name by remember {
        mutableStateOf("")
    }
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
            value = name,
            onValueChange = {name=it},
            label = { Text("please enter name") }
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

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
                auth.createUserWithEmailAndPassword(email,pass)
                    .addOnSuccessListener { result ->

                        val uid = result.user?.uid?:
                        return@addOnSuccessListener


                        val user = User(
                            uid = uid,
                            name = name,
                            email = email
                        )

                        db.reference
                            .child("Users").child(uid)
                            .setValue(user).addOnSuccessListener {
                                Toast.makeText(
                                    context,
                                    "Account created successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate("home")
                            }.addOnFailureListener {
                                Toast.makeText(
                                    context,
                                    it.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }



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
            Text("Signup")
        }


        Button(
            onClick = {
                navController.navigate("login")
            }
        ) {
            Text("take me to login page")
        }
    }
}
