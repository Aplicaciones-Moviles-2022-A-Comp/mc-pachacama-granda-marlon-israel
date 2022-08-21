package com.example.proyectosegundobimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GUI_ResultTransaction : AppCompatActivity() {

    val db = Firebase.firestore
    val banks = db.collection("Bancos")
    var current_user = FirestoreUser("","","","","","","","",0.0,"","","")
    var current_bank = FirestoreBank("","","","")
    var current_transaction = FirestoreTransaction("","","",0.0,"","","","",0.0,"","","",0.0,0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_result_transaction)
    }

    override fun onStart() {
        super.onStart()

        current_user = intent.getParcelableExtra<FirestoreUser>("current_user")!!
        current_bank = intent.getParcelableExtra<FirestoreBank>("current_bank")!!
        current_transaction = intent.getParcelableExtra<FirestoreTransaction>("current_transaction")!!

        val tv_amount_transfer = findViewById<TextView>(R.id.tv_boucher_amount)
        tv_amount_transfer.setText("$ ${String.format("%.2f",current_transaction.transaction_amount)}")
        val tv_concept = findViewById<TextView>(R.id.tv_boucher_concept)
        tv_concept.setText("${current_transaction.transaction_concept}")


        val user_name_destiny = findViewById<TextView>(R.id.tv_boucher_user_des_name)
        user_name_destiny.setText("${current_transaction.user_destiny_name}")
        val num_account_destiny = findViewById<TextView>(R.id.tv_boucher_user_des_num_accou)
        num_account_destiny.setText("Ahorros - ${current_transaction.num_account_destiny}")
        val bank_name_destiny = findViewById<TextView>(R.id.tv_boucher_user_des_bank_name)
        bank_name_destiny.setText("${current_transaction.user_destiny_bank_name}")


        val user_name_souorce = findViewById<TextView>(R.id.tv_boucher_user_src_name)
        user_name_souorce.setText("${current_transaction.user_source_name}")
        val num_account_source = findViewById<TextView>(R.id.tv_boucher_user_src_num_acco)
        num_account_source.setText("Ahorros - ${current_transaction.num_account_source}")


        val tv_amount_transfer_detail = findViewById<TextView>(R.id.tv_boucher_tranfer_value)
        tv_amount_transfer_detail.setText("$ ${String.format("%.2f",current_transaction.transaction_amount)}")
        val tv_comisioin = findViewById<TextView>(R.id.tv_boucher_comisiion)
        tv_comisioin.setText("${String.format("%.2f",current_transaction.transaction_comision)}")
        val tv_total_amount = findViewById<TextView>(R.id.tv_boucher_total_amount)

        var total_amount = current_transaction.transaction_comision + current_transaction.transaction_amount

        tv_total_amount.setText("${String.format("%.2f",total_amount)}")
        current_user.account_balance -= total_amount

        val btn_accept = findViewById<Button>(R.id.btn_boucher_accept)
        btn_accept.setOnClickListener {
            abrirHomeConParametros(GUI_HomeAccount::class.java)
        }

        val btn_menu_home = findViewById<ImageButton>(R.id.btn_transaction2_home)
        btn_menu_home.setOnClickListener {
            abrirHomeConParametros(GUI_HomeAccount::class.java)
        }
        val btn_menu_historial = findViewById<ImageButton>(R.id.btn_transaction2_historial)
        btn_menu_historial.setOnClickListener {
            abrirHomeConParametros(GUI_TransactionsHistorial::class.java)
        }
        val btn_menu_help = findViewById<ImageButton>(R.id.btn_transaction2_help)
        btn_menu_help.setOnClickListener {
            abrirHomeConParametros(GUI_Help::class.java)
        }
        val btn_menu_profile = findViewById<ImageButton>(R.id.btn_transaction2_profile)
        btn_menu_profile.setOnClickListener {
            abrirHomeConParametros(GUI_UserProfile::class.java)
        }
    }

    fun abrirHomeConParametros(
        clase: Class<*>
    ){
        val intent = Intent(this,clase)
        intent.putExtra("current_user",current_user)
        intent.putExtra("current_bank",current_bank)
        startActivity(intent)
    }

}