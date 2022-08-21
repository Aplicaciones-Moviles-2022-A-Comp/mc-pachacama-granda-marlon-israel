package com.example.proyectosegundobimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GUI_TransactionsHistorial : AppCompatActivity() {

    val db = Firebase.firestore
    val banks = db.collection("Bancos")
    var current_user = FirestoreUser("","","","","","","","",0.0,"","","")
    var current_bank = FirestoreBank("","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_transactions_historial)
    }

    override fun onStart() {
        super.onStart()

        current_user = intent.getParcelableExtra<FirestoreUser>("current_user")!!
        current_bank = intent.getParcelableExtra<FirestoreBank>("current_bank")!!

        val recyclerViewTransactionsData = findViewById<RecyclerView>(R.id.recyclerv_transaction_historial)

        val btn_menu_home = findViewById<ImageButton>(R.id.btn_historial_home)
        btn_menu_home.setOnClickListener {
            abrirActividadConParamtros(GUI_HomeAccount::class.java)
        }
        val btn_menu_help = findViewById<ImageButton>(R.id.btn_historial_help)
        btn_menu_help.setOnClickListener {
            abrirActividadConParamtros(GUI_Help::class.java)
        }
        val btn_menu_profile = findViewById<ImageButton>(R.id.btn_historial_profile)
        btn_menu_profile.setOnClickListener {
            abrirActividadConParamtros(GUI_UserProfile::class.java)
        }

        val currentTransactionReference = banks.document("${current_bank.bank_id}")
                                               .collection("ClientAccounts")
                                               .document("${current_user.user_id}")
                                               .collection("Transactions")

        currentTransactionReference.orderBy("transaction_day").get().addOnSuccessListener { result ->

            val transactionsDataList = arrayListOf<FirestoreTransaction>()

            for (document in result){
                transactionsDataList.add(
                    FirestoreTransaction(
                        "${document.data.get("transaction_numAcc_source")}",
                        "${document.data.get("transaction_numAcc_destiny")}",
                        "${document.data.get("transaction_concept")}",
                        document.data.get("transaction_amount").toString().toDouble(),
                        "${document.data.get("transaction_name_source")}",
                        "${document.data.get("transaction_bank_name_source")}",
                        "${document.data.get("transaction_name_destiny")}",
                        "${document.data.get("transaction_bank_name_destiny")}",
                        0.0,
                        "${document.data.get("transaction_day")}",
                        "${document.data.get("transaction_month")}",
                        "${document.data.get("transaction_sign")}",
                        document.data.get("transaction_add_sub_value").toString().toDouble(),
                        document.data.get("transaction_balance_after").toString().toDouble()
                    )
                )
            }

            inicializarRecyclerView(transactionsDataList, this, recyclerViewTransactionsData)

        }


    }

    fun inicializarRecyclerView(
        listaTransacciones: List<FirestoreTransaction>,
        actividad: GUI_TransactionsHistorial,
        recyclerView: RecyclerView
    ) {
        val adaptador = RecyclerView_Transactions(
            actividad,
            listaTransacciones,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
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