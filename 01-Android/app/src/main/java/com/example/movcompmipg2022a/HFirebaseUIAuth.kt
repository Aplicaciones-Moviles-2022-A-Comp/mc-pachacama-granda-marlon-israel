package com.example.movcompmipg2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HFirebaseUIAuth : AppCompatActivity() {
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res: FirebaseAuthUIAuthenticationResult ->
        if (res.resultCode === RESULT_OK) {
            if (res.idpResponse != null) {
                this.seLogeo(res.idpResponse!!)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)


    val btnLogin = findViewById<Button>(R.id.btn_intent_firebase_ui)
    btnLogin.setOnClickListener {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        //Create and Launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        seDeslogeo()
                    }
        }
}

    fun seLogeo(
        res: IdpResponse
    ){
        val btnLogin = findViewById<Button>(R.id.btn_intent_firebase_ui)
        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.visibility = View.VISIBLE
        btnLogin.visibility = View.INVISIBLE
        if(res.isNewUser == true){
            registrarUsuarioPorPrimeraVez(res)
        }
    }

    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse){
        val usuarioLogeado = FirebaseAuth.getInstance().currentUser
        if(usuario.email != null && usuarioLogeado != null){
            val  db = Firebase.firestore //obtenemos referencia
            val roles = arrayListOf("Usuario") //creamos el arreglo de permiso
            val email =usuarioLogeado.email//correo
            val uid = usuarioLogeado.uid//identificador
            val nuevoUsuario = hashMapOf<String,Any>(
                "roles" to roles,
                "uid" to uid,
                "email" to email.toString()
            )
            db.collection("usuario")
                .document(email.toString())
                .set(nuevoUsuario)
                .addOnSuccessListener {
                    //Se creo el usuario
                }
                .addOnFailureListener{
                    //Hubo errores creandio el usuario
                }
        }
    }
        fun seDeslogeo(){
            val btnLogin = findViewById<Button>(R.id.btn_intent_firebase_ui)
            val btnLogout = findViewById<Button>(R.id.btn_logout)
            btnLogout.visibility = View.INVISIBLE
            btnLogin.visibility = View.VISIBLE
        }
}