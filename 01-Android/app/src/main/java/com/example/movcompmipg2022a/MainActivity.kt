package com.example.movcompmipg2022a

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import io.sentry.Sentry
import io.sentry.SentryLevel

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                }
            }
        }
    val contenidoIntentImplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                if (result.data != null) {
                    if (result.data!!.data != null) {
                        val uri: Uri = result.data!!.data!!
                        val cursor = contentResolver.query(
                            uri,
                            null,
                            null,
                            null,
                            null,
                            null
                        )
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(
                            indiceTelefono!!
                        )
                        cursor?.close()
                        Log.i("intent-epn", "Telefono ${telefono}")
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Base de datos sqlite
        EBaseDeDatos.TablaEntrenador = ESqliteHelperEntrenador(this)

        //Sentry.captureMessage("testing SDK setup", SentryLevel.INFO)
        Sentry.captureMessage("testing SDK setup", SentryLevel.INFO)

        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }
        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }
        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener {
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }
        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                contenidoIntentExplicito.launch(intentConRespuesta)
//                startActivityForResult(intentConRespuesta, CODIGO_RESPUESTA_INTENT_IMPLICITO)
            }

        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)
        botonSqlite
            .setOnClickListener {
                irActividad(ECrudEntrenador::class.java)
            }
    }


    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirActividadConParametros(
        clase: Class<*>,
    ) {
        val intentExplicito = Intent(this, clase)
        // Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Adrian")
        intentExplicito.putExtra("apellido", "Eguez")
        intentExplicito.putExtra("edad", 32)
        intentExplicito.putExtra(
            "entrenadorPrincipal",
            BEntrenador(1, "Adrian", "Paleta")
        )

        contenidoIntentExplicito.launch(intentExplicito)
        // startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)// 401
    }

}

