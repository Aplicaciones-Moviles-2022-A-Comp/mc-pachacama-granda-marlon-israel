package com.example.proyectosegundobimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class GUI_NewTransaction : AppCompatActivity() {

    val db = Firebase.firestore
    val banks = db.collection("Bancos")
    val accounts = db.collection("Accounts")
    val typeOfTransaction = db.collection("TransactionsTypes")
    var current_user = FirestoreUser("","","","","","","","",0.0,"","","")
    var account_destiny = FirestoreAccount("","","","","","","","","")
    var current_bank = FirestoreBank("","","","")
    var current_transaction = FirestoreTransaction("","","",0.0,"","","","",0.0,"","","",0.0,0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_new_transaction)
    }

    override fun onStart() {
        super.onStart()

        current_user = intent.getParcelableExtra<FirestoreUser>("current_user")!!
        current_bank = intent.getParcelableExtra<FirestoreBank>("current_bank")!!

        val tv_account_num_source = findViewById<TextView>(R.id.tv_transaction_acount_source)
        tv_account_num_source.setText("${current_user.user_name} - ${current_bank.bank_name} - ${current_bank.bank_location_min}")
        val tv_source_email =  findViewById<TextView>(R.id.tv_transaction_source_mail)
        tv_source_email.setText("${current_user.user_email}")
        val tv_destiny_email = findViewById<TextView>(R.id.tv_transaction_destiny_mail)
        val tv_transaction_comision = findViewById<TextView>(R.id.tv_transaction_comision)

        val in_transaction_concept = findViewById<EditText>(R.id.in_transaction_concept)
        val in_transation_amount = findViewById<EditText>(R.id.in_transaction_amount)

        val sp_account_num_destiny = findViewById<Spinner>(R.id.sp_transaction_account_destiny)

        val back_to_home = findViewById<ImageButton>(R.id.btn_back_home_from_transaction)
        back_to_home.setOnClickListener {
            abrirActividadConParamtros(GUI_HomeAccount::class.java)
        }

        val btn_menu_home = findViewById<ImageButton>(R.id.btn_transaction1_home)
        btn_menu_home.setOnClickListener {
            abrirActividadConParamtros(GUI_HomeAccount::class.java)
        }
        val btn_menu_historial = findViewById<ImageButton>(R.id.btn_transaction1_historial)
        btn_menu_historial.setOnClickListener {
            abrirActividadConParamtros(GUI_TransactionsHistorial::class.java)
        }
        val btn_menu_help = findViewById<ImageButton>(R.id.btn_transaction1_help)
        btn_menu_help.setOnClickListener {
            abrirActividadConParamtros(GUI_Help::class.java)
        }
        val btn_menu_profile = findViewById<ImageButton>(R.id.btn_transaction1_profile)
        btn_menu_profile.setOnClickListener {
            abrirActividadConParamtros(GUI_UserProfile::class.java)
        }

        val btn_confirm_transaction = findViewById<Button>(R.id.btn_confirm_transaction)

        accounts.get().addOnSuccessListener {  result ->

            var accountsList = arrayListOf<FirestoreAccount>()
            var accountsListShow = arrayListOf<String>()

            for (documento in result) {
                if (!documento.data.get("account_num").toString().equals(current_user.account_num)){
                    accountsList.add(
                        FirestoreAccount(
                            documento.id,
                            documento.data.get("account_num").toString(),
                            documento.data.get("user_bank_id").toString(),
                            documento.data.get("user_bank_location_min").toString(),
                            documento.data.get("user_bank_name").toString(),
                            documento.data.get("user_email").toString(),
                            documento.data.get("user_id").toString(),
                            documento.data.get("user_name").toString(),
                            documento.data.get("user_username").toString(),
                        )
                    )
                    accountsListShow.add("${documento.data.get("user_name").toString()} - " +
                            "${documento.data.get("user_bank_name").toString()} - " +
                            "${documento.data.get("user_bank_location_min").toString()}")
                }
            }

            val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, accountsListShow)
            sp_account_num_destiny.adapter = adaptador
            var type_of_transaction = ""
            var comisionAmount = -1.5
            var limit_final = -1.5
            var limit_initial = -1.5
            var percentage_comision = 0.1

            sp_account_num_destiny.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    positionAccountSelected: Int,
                    p3: Long
                ) {
                    account_destiny = accountsList.get(positionAccountSelected)
                    tv_destiny_email.setText("${account_destiny.user_email}")
                    if (current_bank.bank_name.equals(account_destiny.user_bakn_name) and current_bank.bank_location_min.equals(account_destiny.user_bank_location_min) ){
                        type_of_transaction = "Intrabancaria"
                    }else if (  (!current_bank.bank_name.equals(account_destiny.user_bakn_name)) and current_bank.bank_location_min.equals(account_destiny.user_bank_location_min) ){
                        type_of_transaction = "Interbancaria"
                    }else if ( !current_bank.bank_location_min.equals(account_destiny.user_bank_location_min) ){
                        type_of_transaction = "Internacional"
                    }

                    typeOfTransaction.whereEqualTo("transaction_name","${type_of_transaction}").get()
                        .addOnSuccessListener {  typesOfTransactions ->
                            for (transaction in typesOfTransactions){
                                comisionAmount = transaction.data.get("transaction_comision").toString().toDouble()
                                limit_final = transaction.data.get("transaction_limit_final").toString().toDouble()
                                if (transaction.data.get("transaction_name").toString().equals("Internacional")){
                                    limit_initial = transaction.data.get("transaction_limit_initial").toString().toDouble()
                                    percentage_comision = transaction.data.get("transaction_percentage").toString().toDouble()
                                }
                                tv_transaction_comision.setText("$ ${String.format("%.2f", comisionAmount)}")
                            }
                        }

                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            in_transation_amount.setOnFocusChangeListener { view, b ->
                if (!b){
                    var t_amount = in_transation_amount.text.toString().toDouble()

                    if((t_amount + comisionAmount) > current_user.account_balance){
                        MaterialAlertDialogBuilder(this)
                            .setTitle("Advertencia")
                            .setMessage("No tiene fondos suficientes para realizar esta transacción")
                            .setNegativeButton("Entendido") { dialog, which->
                                Log.i("Negative","Transacción supera el saldo de la cuenta")
                                in_transation_amount.text.clear()
                            }
                            .show()
                    }else{

                        if (t_amount <= 1){
                            MaterialAlertDialogBuilder(this)
                                .setTitle("Advertencia")
                                .setMessage("No se puede realizar una transacción menor a $ 1.00")
                                .setNegativeButton("Entendido") { dialog, which->
                                    Log.i("Negative","Transacción menor a 1 dolar")
                                    in_transation_amount.text.clear()
                                }
                                .show()
                        }else if (t_amount > limit_final){
                            MaterialAlertDialogBuilder(this)
                                .setTitle("Advertencia")
                                .setMessage("Las transacciones de tipo ${type_of_transaction} no pueden superar los $ ${String.format("%.2f", limit_final)}")
                                .setNegativeButton("Entendido") { dialog, which->
                                    Log.i("Negative","Transacción supera el limite")
                                    in_transation_amount.text.clear()
                                }
                                .show()
                        }

                        if (type_of_transaction.equals("Internacional") and (t_amount > limit_initial)){
                            comisionAmount = comisionAmount + ( t_amount * (percentage_comision/100) )
                            tv_transaction_comision.setText("${String.format("%.2f", comisionAmount)}")
                        }

                    }
                }
            }

            btn_confirm_transaction.setOnClickListener {

                if(in_transaction_concept.text.toString().equals("") or in_transation_amount.text.toString().equals("")){
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Advertencia")
                        .setMessage("El campo concepto de transacción o valor de transacción están vacios")
                        .setNegativeButton("Entendido") { dialog, which->
                            Log.i("Negative","Transacción sin concepto")
                            in_transation_amount.text.clear()
                        }
                        .show()
                }else{

                    MaterialAlertDialogBuilder(this)
                        .setTitle("Advertencia")
                        .setMessage("¿Esta seguro que desea continuar con la transacción?")
                        .setNegativeButton("Cancelar") { dialog, which->
                            Log.i("Negative","Transacción cancelada")
                        }
                        .setPositiveButton("Continuar") { dialog, which ->
                            //Log.i("Positive","Proceder con la transacción")
                            //Log.i("Tipo de transacción", "${type_of_transaction}")

                            val sdf_day = SimpleDateFormat("dd")
                            val current_day = sdf_day.format(Date())
                            val sdf_month = SimpleDateFormat("MM")
                            var current_month = sdf_month.format(Date())
                            current_month = getMonthString(current_month)

                            // Modify the data for the source account

                            var accountSourceReference = banks.document("${current_bank.bank_id}")
                                                              .collection("ClientAccounts")
                                                              .document("${current_user.user_id}")

                            var transactionSourceReference = banks.document("${current_bank.bank_id}")
                                                                  .collection("ClientAccounts")
                                                                  .document("${current_user.user_id}")
                                                                  .collection("Transactions")

                            accountSourceReference.get().addOnSuccessListener { sourceAccountResult ->

                                var finalAmountSource = in_transation_amount.text.toString().toDouble()
                                finalAmountSource = finalAmountSource + comisionAmount

                                var currentBalance = sourceAccountResult.data?.get("account_balance").toString().toDouble()
                                currentBalance = currentBalance - finalAmountSource

                                var transactionSourceData = hashMapOf(
                                    "transaction_day" to current_day,
                                    "transaction_month" to current_month,
                                    "transaction_concept" to in_transaction_concept.text.toString(),
                                    "transaction_name_source" to current_user.user_name,
                                    "transaction_numAcc_source" to current_user.account_num,
                                    "transaction_name_destiny" to account_destiny.user_name,
                                    "transaction_numAcc_destiny" to account_destiny.account_num,
                                    "transaction_bank_name_source" to current_bank.bank_name,
                                    "transaction_bank_name_destiny" to account_destiny.user_bakn_name,
                                    "transaction_sign" to "-",
                                    "transaction_amount" to in_transation_amount.text.toString(),
                                    "transaction_add_sub_value" to finalAmountSource,
                                    "transaction_balance_after" to currentBalance.toString().toDouble(),
                                )

                                Log.i("Final Amount Source","${finalAmountSource} = ${comisionAmount} + ${in_transation_amount.text}")

                                transactionSourceReference.add(transactionSourceData).addOnSuccessListener {
                                    Log.i("Transaction","SUCCESS")
                                }

                                accountSourceReference.update("account_balance",currentBalance)

                            }

                            // Modify the data for the destiny account

                            var accountDestinyReference = banks.document("${account_destiny.user_bank_id}")
                                                               .collection("ClientAccounts")
                                                               .document("${account_destiny.user_id}")

                            var transactionDestinyReference = banks.document("${account_destiny.user_bank_id}")
                                                                   .collection("ClientAccounts")
                                                                   .document("${account_destiny.user_id}")
                                                                   .collection("Transactions")

                            accountDestinyReference.get().addOnSuccessListener { destinyAccountResult ->

                                var finalAmountDestiny = in_transation_amount.text.toString().toDouble()
                                var currentBalance = destinyAccountResult.data?.get("account_balance").toString().toDouble()
                                currentBalance = currentBalance + finalAmountDestiny

                                var transactionDestinyData = hashMapOf(
                                    "transaction_day" to current_day,
                                    "transaction_month" to current_month,
                                    "transaction_concept" to in_transaction_concept.text.toString(),
                                    "transaction_name_source" to current_user.user_name,
                                    "transaction_numAcc_source" to current_user.account_num,
                                    "transaction_name_destiny" to account_destiny.user_name,
                                    "transaction_numAcc_destiny" to account_destiny.account_num,
                                    "transaction_bank_name_source" to current_bank.bank_name,
                                    "transaction_bank_name_destiny" to account_destiny.user_bakn_name,
                                    "transaction_sign" to "+",
                                    "transaction_amount" to in_transation_amount.text.toString(),
                                    "transaction_add_sub_value" to finalAmountDestiny.toString().toDouble(),
                                    "transaction_balance_after" to currentBalance.toString(),
                                )

                                Log.i("Final Amount Destiny","${finalAmountDestiny}")

                                transactionDestinyReference.add(transactionDestinyData).addOnSuccessListener {
                                    Log.i("Transaction","SUCCESS")
                                }

                                current_transaction.num_account_source = current_user.account_num
                                current_transaction.num_account_destiny = account_destiny.account_num
                                current_transaction.transaction_concept = in_transaction_concept.text.toString()
                                current_transaction.transaction_amount = in_transation_amount.text.toString().toDouble()
                                current_transaction.user_source_name = current_user.user_name
                                current_transaction.user_source_bank_name = current_bank.bank_name
                                current_transaction.user_destiny_name = account_destiny.user_name
                                current_transaction.user_destiny_bank_name = account_destiny.user_bakn_name
                                current_transaction.transaction_comision = comisionAmount
                                current_transaction.transaction_day = current_day
                                current_transaction.transaction_month = current_month

                                accountDestinyReference.update("account_balance",currentBalance)

                                Toast.makeText(this,"Transacción realizada exitosamente",Toast.LENGTH_SHORT).show()

                                abrirBoucher(GUI_ResultTransaction::class.java)

                            }


                        }
                        .show()

                }

            }
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

    fun abrirBoucher(
        clase: Class<*>
    ){
        val intent = Intent(this,clase)
        intent.putExtra("current_user",current_user)
        intent.putExtra("current_bank",current_bank)
        intent.putExtra("current_transaction",current_transaction)
        startActivity(intent)
    }

    fun getMonthString (month:String):String{
        var month_res = ""
        when(month){
            "01" -> month_res = "Ene"
            "02" -> month_res = "Feb"
            "03" -> month_res = "Mar"
            "04" -> month_res = "Abr"
            "05" -> month_res = "May"
            "06" -> month_res = "Jun"
            "07" -> month_res = "Jul"
            "08" -> month_res = "Ago"
            "09" -> month_res = "Sep"
            "10" -> month_res = "Oct"
            "11" -> month_res = "Nov"
            "12" -> month_res = "Dic"
        }
        return month_res
    }

}