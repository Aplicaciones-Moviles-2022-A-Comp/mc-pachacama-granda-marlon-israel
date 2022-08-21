package com.example.proyectosegundobimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class GUI_LoginPassword : AppCompatActivity() {

    val db = Firebase.firestore
    val banks = db.collection("Bancos")
    val current_user = FirestoreUser("","","","","","","","",0.0,"","","")
    val current_bank = FirestoreBank("","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_login_password)
    }

    override fun onStart() {
        super.onStart()

        val polibank_reference = banks.whereEqualTo("bank_name","Poli Banco")

        val in_username = findViewById<EditText>(R.id.in_login_user_username)
        val in_password = findViewById<EditText>(R.id.in_login_user_pass)

        val btn_confirm_login = findViewById<Button>(R.id.btn_confirm_login)
        btn_confirm_login.setOnClickListener {
            polibank_reference.get().addOnSuccessListener { result ->
                for (document in result){
                    current_bank.bank_id = document.id
                    current_bank.bank_name = document.data.get("bank_name").toString()
                    current_bank.bank_location = document.data.get("bank_location").toString()
                    current_bank.bank_location_min = document.data.get("bank_location_min").toString()
                }
                var accountReference = banks.document("${current_bank.bank_id}")
                                            .collection("ClientAccounts")
                                            .whereEqualTo("user_username","${in_username.text}")

                accountReference.get().addOnSuccessListener {  result ->
                    if(result.isEmpty){
                        Toast.makeText(this,"Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }else{
                        for (document in result){
                            if(document.data.get("account_password").toString().equals("${in_password.text}")){

                                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                                val currentDate = sdf.format(Date())

                                current_user.user_id = document.id.toString()
                                current_user.user_bank = current_bank.bank_id
                                current_user.user_ci = document.data.get("user_ci").toString()
                                current_user.user_email = document.data.get("user_email").toString()
                                current_user.user_location = document.data.get("user_location").toString()
                                current_user.user_name = document.data.get("user_name").toString()
                                current_user.user_phone = document.data.get("user_phone").toString()
                                current_user.user_username = document.data.get("user_username").toString()
                                current_user.account_balance =  document.data.get("account_balance").toString().toDouble()
                                current_user.account_num = document.data.get("account_num").toString()
                                current_user.account_pass = document.data.get("account_password").toString()
                                current_user.account_last_access = currentDate.toString()

                                Toast.makeText(this,"Bienvenido", Toast.LENGTH_SHORT).show()

                                val openHomeUser = Intent(this, GUI_HomeAccount::class.java)
                                openHomeUser.putExtra("current_user",current_user)
                                openHomeUser.putExtra("current_bank",current_bank)
                                startActivity(openHomeUser)

                                Log.i("UserAccount","${current_user.account_balance}")

                            }else{
                                Toast.makeText(this,"Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }.addOnFailureListener{
                    Log.i("ERROR","Error retrieving the account info")
                }
            }.addOnFailureListener{
                Log.i("ERROR","Error retrieving the bank id")
            }
        }

        val btn_cancel_login = findViewById<Button>(R.id.btn_cancel_login)
        btn_cancel_login.setOnClickListener {
            val backToSelectLogin = Intent(this, GUI_SelectTypeLogin::class.java)
            startActivity(backToSelectLogin)
        }

    }

}