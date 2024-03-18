package com.estudo.app_megasena

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.estudo.app_megasena.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val button: Button = binding.button
        val numeros = binding.txtNumeros

        numeros.text = "Clique para sortear os números"

       /* var qtdNumeros: ArrayList<Int> = arrayListOf()
        for (i in 6..15) {  // 1..10 é um intervalo que vai de 1 a 10
            qtdNumeros.add(i)
        }

        var spinnerData = qtdNumeros.map {
            it.toString()
        }

        binding.spinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerData)

        var spinnerValue = binding.spinner.selectedItem
        */

        button.setOnClickListener {
            numeros.text = gerarNumeros(6, 60).joinToString("-")
        }


    }

    private fun gerarNumeros(qtde: Int, max: Int): MutableSet<Int> {
        val listaNumeros = mutableSetOf<Int>()
        var incremento: Int = 0
        val random = Random()

        while (incremento < qtde) {

            val number = random.nextInt(max)
            listaNumeros.add(number + 1)

            incremento++

        }

        return listaNumeros.sortedDescending().reversed().toMutableSet()
    }
}