package sandoval.eduardo.firebasenotes.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthSettings
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sandoval.eduardo.firebasenotes.model.UserModel

class LoginViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    var showAlert by mutableStateOf(false)


    // Iniciar sesión
    fun login(email: String, password: String, onSuccess:() -> Unit){
        viewModelScope.launch{
            try{
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            onSuccess()
                        }else{
                            //Error de firebase
                            Log.d("ERROR EN FIREBASE", "USUARIO Y CONTRASEÑA INCORRECTOS")
                            showAlert = true
                        }
                    }
            }catch(e: Exception){
                Log.d("ERROR EN JETPACK", "ERROR: ${e.localizedMessage}")
            }
        }
    }

    //Registrar usuario
    fun createUser(email: String, password: String, username: String ,onSuccess:() -> Unit){
        viewModelScope.launch{
            try{
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            saveUser(username)
                            onSuccess()
                        }else{
                            //Error de firebase
                            Log.d("ERROR EN FIREBASE", "ERROR AL CREAR EL USUARIO")
                            showAlert = true
                        }
                    }
            }catch(e: Exception){
                Log.d("ERROR EN JETPACK", "ERROR: ${e.localizedMessage}")
            }
        }
    }

    private fun saveUser(username: String){
        // Extraer campos de los registros en Firebase
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        viewModelScope.launch(Dispatchers.IO){
            val user = UserModel(
                userId = id.toString(),
                email = email.toString(),
                username = username
            )

            //Guardar en Firebase
            FirebaseFirestore.getInstance().collection("Users")
                .add(user)
                .addOnSuccessListener {
                    Log.d("EXITO AL GUARDAR", "EXITO AL GUARDAR EN FIRESTORE")
                }.addOnFailureListener{
                    Log.d("ERROR AL GUARDAR", "ERROR AL GUARDAR EN FIRESTORE")
                }
        }

    }

    fun closeAlert(){
        showAlert = false
    }
}