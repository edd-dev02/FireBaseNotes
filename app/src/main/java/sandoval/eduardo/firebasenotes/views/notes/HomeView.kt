package sandoval.eduardo.firebasenotes.views.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import sandoval.eduardo.firebasenotes.components.CardNote
import sandoval.eduardo.firebasenotes.viewModels.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController:NavController, notesVM: NotesViewModel){

    LaunchedEffect(Unit) {
        notesVM.fetchNotes()
    }
    Scaffold(
        topBar = {
           TopAppBar(
             title = { Text(text = "Mis Notas") },
             navigationIcon = {
                 IconButton(onClick = {
                     notesVM.signOut()
                     navController.popBackStack()
                 }) {
                     Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")
                 }
             },
             actions = {
                 IconButton(onClick = {
                     navController.navigate("AddNoteView")
                 }) {
                     Icon(imageVector = Icons.Default.Add, contentDescription = "")
                 }
             }
           )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            val datos by notesVM.notesData.collectAsState()

            LazyColumn {
                items(datos){ item ->
                    CardNote(title = item.title, note = item.note, date = item.date) {
                        // Editar...
                        navController.navigate("EditNoteView/${item.idDoc}")
                    }
                }
            }

        }
    }
}