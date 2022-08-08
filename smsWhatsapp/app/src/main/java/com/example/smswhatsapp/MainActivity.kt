package com.example.smswhatsapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    var etMsj: EditText? = null
    var etCel: EditText? = null
    var btnSelec: FloatingActionButton? = null
    var btnW: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etMsj = findViewById(R.id.editTextPersonName)
        etCel = findViewById(R.id.editTextPersonNumber)
        btnW = findViewById(R.id.btnW)
        btnSelec = findViewById(R.id.btnSelec)

        if (ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.SEND_SMS),
                1
            )
        }

        btnSelec.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, 1)
        })

        btnW.setOnClickListener(View.OnClickListener {
            if (etCel.getText().toString().isEmpty()) {
                try {
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(Intent.EXTRA_TEXT, etMsj.getText().toString())
                    sendIntent.type = "text/plain"
                    sendIntent.setPackage("com.whatsapp")
                    startActivity(sendIntent)
                } catch (e: Exception) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error de Whatsapp.\nInstale la App",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                try {
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_VIEW
                    val uri = "whatsapp://send?phone=" + etCel.getText()
                        .toString() + "&text=" + etMsj.getText().toString()
                    sendIntent.data = Uri.parse(uri)
                    startActivity(sendIntent)
                } catch (e: Exception) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error de Whatsapp.\nInstale la App",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val uri = data!!.data
            val cursor = contentResolver.query(uri!!, null, null, null, null)
            if (cursor != null && cursor.moveToFirst()) {
                val indiceName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val indiceNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val nombre = cursor.getString(indiceName)
                var numero = cursor.getString(indiceNumber)
                numero = numero.replace("(", "").replace(")", "").replace(" ", "").replace("-", "")
                etMsj!!.setText(nombre)
                etCel!!.setText(numero)
            }
        }
    }
}