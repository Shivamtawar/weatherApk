package com.example.firebaseintegration.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebaseintegration.model.Notes
import com.example.firebaseintegration.model.Randomness
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.random.Random

@Composable
fun HomeScreen() {


    val context = LocalContext.current
    val db = FirebaseDatabase.getInstance()

    var noteTitle by remember {
        mutableStateOf("")
    }
    var noteText by remember {
        mutableStateOf("")
    }

    var noteList by remember {
        mutableStateOf<List<Notes>>(emptyList())
    }

    val uid = FirebaseAuth.getInstance().currentUser?.uid

    LaunchedEffect(Unit) {

        uid?.let{
            db.reference
                .child("Notes")
                .child(it)

                .addValueEventListener(
                    object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {

                            val templist = mutableListOf<Notes>()
                            for(child in snapshot.children)
                            {
                                val note = child.getValue(
                                    Notes::class.java
                                )
                                note?.let{
                                    templist.add(it)
                                }
                            }
                            noteList = templist

                        }

                        override fun onCancelled(p0: DatabaseError) {

                        }
                    }
                )

        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = noteTitle,
            onValueChange = {noteTitle = it},
            label = {Text("Enter note Title")}

        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = noteText,
            onValueChange = {noteText = it},
            label = {Text("Enter note ")}

        )

        Button(
            onClick = {

                val uid = FirebaseAuth.getInstance().currentUser?.uid?:return@Button

                val noteId = db.reference
                    .child("Notes")
                    .child(uid)
                    .push()
                    .key ?: return@Button

                val note = Notes(
                    id = noteId,
                    noteText = noteText,
                    noteTitle = noteTitle
                )

                db.reference
                    .child("Notes").child(uid).child(noteId)
                    .setValue(note).addOnSuccessListener {
                        Toast.makeText(
                            context,
                            "Account created successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }



            }
        ) {
            Text("save note")
        }

        Spacer(modifier = Modifier.height(22.dp))


        Text("All notes",
            fontSize = 28.sp

        )

        Spacer(modifier = Modifier.height(22.dp))


        LazyColumn() {
            items(noteList){ note ->

                NoteCard(note)

            }
        }




    }
}

@Composable
fun NoteCard(
    note:Notes
){

    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)

        ) {
            Text(note.noteTitle)
            Text(note.noteText)
        }
    }
}