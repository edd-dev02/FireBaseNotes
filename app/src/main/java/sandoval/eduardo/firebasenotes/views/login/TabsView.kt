package sandoval.eduardo.firebasenotes.views.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import sandoval.eduardo.firebasenotes.viewModels.LoginViewModel

@Composable
fun TabsView(navController: NavController, loginVM: LoginViewModel) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Iniciar Sesión", "Crear Cuenta")

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                TabRow(
                    selectedTabIndex = selectedTab,
                    contentColor = Color.Black,
                    //containerColor = Color.LightGray,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTab])
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(text = title) }
                        )
                    }
                }
                when (selectedTab) {
                    0 -> LoginView(navController, loginVM)
                    1 -> RegisterView(navController, loginVM)
                }
            }
        }
    }
}

