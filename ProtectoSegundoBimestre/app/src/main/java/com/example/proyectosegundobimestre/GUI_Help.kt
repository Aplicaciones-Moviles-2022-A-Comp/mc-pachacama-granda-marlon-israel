package com.example.proyectosegundobimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import android.widget.ImageButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GUI_Help : AppCompatActivity() {

    val db = Firebase.firestore
    val faq_collection = db.collection("FAQ")
    var current_user = FirestoreUser("","","","","","","","",0.0,"","","")
    var current_bank = FirestoreBank("","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_help)
    }

    override fun onStart() {
        super.onStart()

        current_user = intent.getParcelableExtra<FirestoreUser>("current_user")!!
        current_bank = intent.getParcelableExtra<FirestoreBank>("current_bank")!!

        val ex_list_adapter = findViewById<ExpandableListView>(R.id.expandable_list_view)

        val btn_menu_home = findViewById<ImageButton>(R.id.btn_help_home)
        btn_menu_home.setOnClickListener {
            abrirActividadConParamtros(GUI_HomeAccount::class.java)
        }
        val btn_menu_historial = findViewById<ImageButton>(R.id.btn_help_historial)
        btn_menu_historial.setOnClickListener {
            abrirActividadConParamtros(GUI_TransactionsHistorial::class.java)
        }
        val btn_menu_profile = findViewById<ImageButton>(R.id.btn_help_profile)
        btn_menu_profile.setOnClickListener {
            abrirActividadConParamtros(GUI_UserProfile::class.java)
        }

        faq_collection.get().addOnSuccessListener { result ->

            val header:MutableList<String> = ArrayList()
            val body:MutableList<String> = ArrayList()

            for (document in result){
                Log.i("FAQ","${document.data.get("faq_question")}")
                header.add("${document.data.get("faq_question")}")
                body.add("${document.data.get("faq_answer")}")
            }

            ex_list_adapter.setAdapter(ExpandableListAdapter(this, header,body))

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