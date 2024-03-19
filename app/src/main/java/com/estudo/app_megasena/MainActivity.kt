package com.estudo.app_megasena

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.estudo.app_megasena.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val button: Button = binding.button
        val resultado: TextView = binding.txtNumeros
        val edtTxt: EditText = binding.edtNumeros
        val textHistorico: TextView = binding.txtHistorico


        resultado.text = ""

        button.setOnClickListener {
            prefs = getSharedPreferences("db", Context.MODE_PRIVATE)
            val result = prefs.getString("result", null)

            if (result != null) {
                textHistorico.text = "$result"
            }

            val numero = edtTxt.text.toString()
            gerarNumeros(numero, resultado)
        }
    }

    private fun gerarNumeros(qtde: String, textView: TextView) {
        if (qtde.isNotEmpty()) {

            val qtdeNum = qtde.toInt()

            if (qtdeNum >= 6 && qtdeNum <= 15) {

                val listaNumeros = mutableSetOf<Int>()
                val random = Random()

                while (true) {
                    val valor = random.nextInt(60)
                    listaNumeros.add(valor + 1)

                    if (listaNumeros.size == qtdeNum) {
                        break
                    }
                }
                textView.text = listaNumeros.joinToString(" - ")

                prefs.edit().apply() {
                    putString("result", textView.text.toString())
                    apply()
                }

            } else {
                Toast.makeText(this, "Insira um número entre 6 e 15", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Insira uma quantidade de números", Toast.LENGTH_SHORT).show()
        }
    }
}