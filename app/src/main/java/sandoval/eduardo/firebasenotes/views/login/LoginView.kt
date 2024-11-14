package sandoval.eduardo.firebasenotes.views.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import sandoval.eduardo.firebasenotes.components.Alert
import sandoval.eduardo.firebasenotes.viewModels.LoginViewModel

@Composable
fun LoginView(navController: NavController, loginVM: LoginViewModel) {
    Spacer(modifier = Modifier.height(40.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ){
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        OutlinedTextField(value = email,
            onValueChange = {email = it},
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )

        OutlinedTextField(value = password,
            onValueChange = {password = it},
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        
        Button(onClick = {
            loginVM.login(email, password){
                navController.navigate("Home")
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
        ) {
            Text(text = "Entrar")
        }

        if(loginVM.showAlert){
            Alert(
                title = "Alerta",
                message = "Usuario y/o Contraseña incorrectos",
                confirmText = "Aceptar",
                onConfirm = { loginVM.closeAlert() }) {

            }
        }

    }

}