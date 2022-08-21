package com.example.proyectosegundobimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GUI_UserProfile : AppCompatActivity() {

    val db = Firebase.firestore
    val banks = db.collection("Bancos")
    var current_user = FirestoreUser("","","","","","","","",0.0,"","","")
    var current_bank = FirestoreBank("","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_user_profile)
    }

    override fun onStart() {
        super.onStart()

        current_user = intent.getParcelableExtra<FirestoreUser>("current_user")!!
        current_bank = intent.getParcelableExtra<FirestoreBank>("current_bank")!!

        val tv_user_name = findViewById<TextView>(R.id.tv_profile_user_name)
        tv_user_name.setText("${current_user.user_name}")
        val tv_user_username = findViewById<TextView>(R.id.tv_profile_user_username)
        tv_user_username.setText("${current_user.user_username}")
        val tv_user_ci = findViewById<TextView>(R.id.tv_profile_user_ci)
        tv_user_ci.setText("${current_user.user_ci}")
        val tv_user_phone = findViewById<TextView>(R.id.tv_profile_user_phone)
        tv_user_phone.setText("${current_user.user_phone}")
        val tv_user_mail = findViewById<TextView>(R.id.tv_profile_user_email)
        tv_user_mail.setText("${current_user.user_email}")
        val tv_user_location = findViewById<TextView>(R.id.tv_profile_user_location)
        tv_user_location.setText("${current_user.user_location}")

        val btn_logout = findViewById<Button>(R.id.btn_logout)
        btn_logout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val btn_change_pass = findViewById<Button>(R.id.btn_change_pass)
        btn_change_pass.setOnClickListener {
            abrirActividadConParamtros(GUI_ChangePassword::class.java)
        }



        val btn_menu_home = findViewById<ImageButton>(R.id.btn_profile1_home)
        btn_menu_home.setOnClickListener {
            abrirActividadConParamtros(GUI_HomeAccount::class.java)
        }
        val btn_menu_historial = findViewById<ImageButton>(R.id.btn_profile1_historial)
        btn_menu_historial.setOnClickListener {
            abrirActividadConParamtros(GUI_TransactionsHistorial::class.java)
        }
        val btn_menu_help = findViewById<ImageButton>(R.id.btn_profile1_help)
        btn_menu_help.setOnClickListener {
            abrirActividadConParamtros(GUI_Help::class.java)
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