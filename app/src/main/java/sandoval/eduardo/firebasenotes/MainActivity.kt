package sandoval.eduardo.firebasenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import sandoval.eduardo.firebasenotes.navigation.NavManager
import sandoval.eduardo.firebasenotes.ui.theme.FireBaseNotesTheme
import sandoval.eduardo.firebasenotes.viewModels.LoginViewModel
import sandoval.eduardo.firebasenotes.viewModels.NotesViewModel
import sandoval.eduardo.firebasenotes.views.login.TabsView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ViewModels
        val loginVM : LoginViewModel by viewModels()
        val notesVM : NotesViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            FireBaseNotesTheme {
              NavManager(loginVM, notesVM)
            }
        }
    }
}

