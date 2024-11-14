package sandoval.eduardo.firebasenotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import sandoval.eduardo.firebasenotes.viewModels.LoginViewModel
import sandoval.eduardo.firebasenotes.viewModels.NotesViewModel
import sandoval.eduardo.firebasenotes.views.login.BlankView
import sandoval.eduardo.firebasenotes.views.login.TabsView
import sandoval.eduardo.firebasenotes.views.notes.AddNoteView
import sandoval.eduardo.firebasenotes.views.notes.EditNoteView
import sandoval.eduardo.firebasenotes.views.notes.HomeView

@Composable
fun NavManager(loginVM: LoginViewModel, notesVM: NotesViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login"){

        composable("Blank"){
            BlankView(navController)
        }
        composable("Login"){
            TabsView(navController, loginVM)
        }
        composable("Home"){
            HomeView(navController, notesVM)
        }
        composable("AddNoteView"){
            AddNoteView(navController, notesVM)
        }
        composable("EditNoteView/{idDoc}", arguments = listOf(
            navArgument("idDoc"){ type = NavType.StringType }
        )){
            val idDoc = it.arguments?.getString("idDoc") ?: ""
            EditNoteView(navController, notesVM, idDoc = idDoc)
        }
    }
}
