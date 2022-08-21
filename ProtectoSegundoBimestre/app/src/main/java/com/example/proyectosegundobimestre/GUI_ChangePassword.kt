package com.example.proyectosegundobimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GUI_ChangePassword : AppCompatActivity() {

    val db = Firebase.firestore
    val banks = db.collection("Bancos")
    var current_user = FirestoreUser("","","","","","","","",0.0,"","","")
    var current_bank = FirestoreBank("","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_change_password)
    }

    override fun onStart() {
        super.onStart()

        current_user = intent.getParcelableExtra<FirestoreUser>("current_user")!!
        current_bank = intent.getParcelableExtra<FirestoreBank>("current_bank")!!

        val in_current_pass = findViewById<EditText>(R.id.in_changepass_current_pass)
        val in_new_pass = findViewById<EditText>(R.id.in_changepass_new_pass)
        val in_new_pass_confirmation = findViewById<EditText>(R.id.in_changepass_new_pass_confirmation)

        val btn_change_pass_confirmation = findViewById<Button>(R.id.btn_change_pass_confirmation)
        btn_change_pass_confirmation.setOnClickListener {
            if (in_current_pass.text.toString().equals(current_user.account_pass)){
                Log.i("CurrentPass","Is equal")
                if (in_new_pass.text.toString().equals("") or in_new_pass_confirmation.text.toString().equals("")){
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Advertencia")
                        .setMessage("Contraseña actual no válida")
                        .setNegativeButton("Entendido") { dialog, which->
                            Log.i("Negative","Current pass wrong")
                        }
                        .show()
                }else {
                    if(in_new_pass.text.toString().equals(in_new_pass_confirmation.text.toString())){
                        Log.i("Change Pass","Approbed")

                        banks.document("${current_bank.bank_id}")
                            .collection("ClientAccounts")
                            .document("${current_user.user_id}")
                            .update("account_password","${in_new_pass.text.toString()}")

                        current_user.account_pass = in_new_pass.text.toString()
                        Toast.makeText(this,"Contraseña actualizada exitosamente",Toast.LENGTH_SHORT).show()

                        abrirActividadConParamtros(GUI_UserProfile::class.java)

                    }else{
                        MaterialAlertDialogBuilder(this)
                            .setTitle("Advertencia")
                            .setMessage("La nueva contraseña y su confirmación no coinciden")
                            .setNegativeButton("Entendido") { dialog, which->
                                Log.i("Negative","Current pass wrong")
                            }
                            .show()
                    }
                }
            }else{
                MaterialAlertDialogBuilder(this)
                    .setTitle("Advertencia")
                    .setMessage("Contraseña actual incorrecta")
                    .setNegativeButton("Entendido") { dialog, which->
                        Log.i("Negative","Current pass wrong")
                        in_current_pass.text.clear()
                    }
                    .show()
            }
        }
        val btn_change_pass_cancel = findViewById<Button>(R.id.btn_change_pass_cancel)
        btn_change_pass_cancel.setOnClickListener {
            abrirActividadConParamtros(GUI_UserProfile::class.java)
        }


        val btn_menu_home = findViewById<ImageButton>(R.id.btn_profile2_home)
        btn_menu_home.setOnClickListener {
            abrirActividadConParamtros(GUI_HomeAccount::class.java)
        }
        val btn_menu_historial = findViewById<ImageButton>(R.id.btn_profile2_historial)
        btn_menu_historial.setOnClickListener {
            abrirActividadConParamtros(GUI_TransactionsHistorial::class.java)
        }
        val btn_menu_help = findViewById<ImageButton>(R.id.btn_profile2_help)
        btn_menu_help.setOnClickListener {
            abrirActividadConParamtros(GUI_Help::class.java)
        }
        val btn_menu_profile = findViewById<ImageButton>(R.id.btn_profile2_profile)
        btn_menu_profile.setOnClickListener {
            abrirActividadConParamtros(GUI_UserProfile::class.java)
        }


    }

    fun abrirActividadConParamtros(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        intent.putExtra("current_user",current_user)
        intent.putExtra("current_bank",current_bank)
        startActivity(intent)
    }

}