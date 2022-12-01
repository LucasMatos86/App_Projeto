package com.example.biografia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.biografia.databinding.ActivityBioMainBinding


class MainBioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBioMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBioMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btBioLucas.setOnClickListener {
            IrParaBioLucas()
        }

    }

    private fun IrParaBioLucas(){
        val telaLucas = Intent(this, BiografiaLucas::class.java)
        startActivity(telaLucas)
    }



}