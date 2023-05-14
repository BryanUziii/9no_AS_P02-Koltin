package com.example.p02kotlin

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private var btnCalcular: Button? = null
    private var btnLimpiar: Button? = null
    private var btnCerrar: Button? = null


    private var lblMostrarResultado: TextView? = null
    private var inputAltura: EditText? = null
    private var inputPeso: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val formato = DecimalFormat("#.##")

        //Relacion de los objetos Java con las vistas xml

        //Relacion de los objetos Java con las vistas xml
        btnCalcular = findViewById<View>(R.id.btnCalcular) as Button
        btnLimpiar = findViewById<View>(R.id.btnLimpiar) as Button
        btnCerrar = findViewById<View>(R.id.btnCerrar) as Button

        inputAltura = findViewById<View>(R.id.inputAltura) as EditText
        inputPeso = findViewById<View>(R.id.inputPeso) as EditText
        lblMostrarResultado = findViewById<View>(R.id.lblMostrarResultado) as TextView


        btnCalcular!!.setOnClickListener(View.OnClickListener {
            val altura = inputAltura!!.text.toString()
            val peso = inputPeso!!.text.toString()
            if (TextUtils.isEmpty(altura) || TextUtils.isEmpty(peso)) {
                // Falta capturar el nombre
                Toast.makeText(
                    this@MainActivity,
                    "Favor Llene Todos Los Campos",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            try {
                val alturaEnCm = altura.toDouble()
                val pesoEnKg = peso.toDouble()
                val alturaEnMetros = alturaEnCm / 100
                val IMC = pesoEnKg / Math.pow(alturaEnMetros, 2.0)
                val IMCFormateado = formato.format(IMC)
                lblMostrarResultado!!.text = "$IMCFormateado kg/m2"
                inputAltura!!.hint = "" + alturaEnCm
                inputPeso!!.hint = "" + pesoEnKg
                inputAltura!!.setText("")
                inputPeso!!.setText("")
            } catch (e: NumberFormatException) {
                // Captura de excepción por si el usuario ingresa un valor no numérico
                Toast.makeText(
                    this@MainActivity,
                    "Los campos de entrada deben ser numéricos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        btnLimpiar!!.setOnClickListener {
            inputAltura!!.setText("")
            inputPeso!!.setText("")
            lblMostrarResultado!!.text = ""
        }

        btnCerrar!!.setOnClickListener { finish() }


    }
}