package com.example.proyectosegundobimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

class GUI_HomeAccount : AppCompatActivity() {

    val db = Firebase.firestore
    val banks = db.collection("Bancos")
    var current_user = FirestoreUser("","","","","","","","",0.0,"","","")
    var current_bank = FirestoreBank("","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_home_account)
    }

    override fun onStart() {
        super.onStart()

        current_user = intent.getParcelableExtra<FirestoreUser>("current_user")!!
        current_bank = intent.getParcelableExtra<FirestoreBank>("current_bank")!!

        val lb_letter = findViewById<TextView>(R.id.tv_home_letter)
        lb_letter.setText("${current_user.user_name?.take(1)}")
        val lb_name = findViewById<TextView>(R.id.tv_home_name)
        lb_name.setText("Hola, ${current_user.user_name}")
        val lb_last_access = findViewById<TextView>(R.id.tv_home_last_access)
        lb_last_access.setText("Último ingreso: ${current_user.account_last_access}")
        val lb_account_num = findViewById<TextView>(R.id.tv_home_account_num)
        lb_account_num.setText("${current_user.account_num}")
        val lb_account_balance = findViewById<TextView>(R.id.tv_home_account_balance)
        lb_account_balance.setText("$ ${String.format("%.2f",current_user.account_balance)}")

        val btn_new_transaction =  findViewById<ImageButton>(R.id.btn_new_transaction)
        btn_new_transaction.setOnClickListener {
            abrirActividadConParametros(GUI_NewTransaction::class.java)
        }

        val btn_menu_historial = findViewById<ImageButton>(R.id.btn_home_historial)
        btn_menu_historial.setOnClickListener {
            abrirActividadConParametros(GUI_TransactionsHistorial::class.java)
        }
        val btn_menu_help = findViewById<ImageButton>(R.id.btn_home_help)
        btn_menu_help.setOnClickListener {
            abrirActividadConParametros(GUI_Help::class.java)
        }
        val btn_menu_profile = findViewById<ImageButton>(R.id.btn_home_profile)
        btn_menu_profile.setOnClickListener {
            abrirActividadConParametros(GUI_UserProfile::class.java)
        }

    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        intent.putExtra("current_user",current_user)
        intent.putExtra("current_bank",current_bank)
        startActivity(intent)
    }

}