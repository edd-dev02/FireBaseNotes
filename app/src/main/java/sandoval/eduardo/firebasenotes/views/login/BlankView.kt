package sandoval.eduardo.firebasenotes.views.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

// Decidir si vamos a login o a Home dependiendo la sesión

@Composable
fun BlankView(navController: NavController){
    /*
    LaunchedEffect(Unit) {
        if(!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate("Home")
        }else{
            navController.navigate("Login")
        }
    }
     */
    val auth = remember { FirebaseAuth.getInstance() }
    var isLoggedIn by remember { mutableStateOf(false) }

    // AuthStateListener
    DisposableEffect(auth) {
        val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            isLoggedIn = firebaseAuth.currentUser != null
        }
        auth.addAuthStateListener(listener)
        onDispose { auth.removeAuthStateListener(listener) }
    }

    // Navegación
    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate("Home")
        } else {
            navController.navigate("Login")
        }
    }
}