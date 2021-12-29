package com.example.albumapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import com.example.albumapp.databinding.ActivityLoginBinding
import com.example.albumapp.databinding.ActivityOptionsBinding
import com.google.android.material.appbar.CollapsingToolbarLayout

class OptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnCLick()
    }

    private fun btnCLick(){
        binding.btnAddPet.setOnClickListener {
           val intent = Intent(this, AddPetActivity::class.java)
            startActivity(intent)
        }
        binding.btnMyPets.setOnClickListener {
            val intent = Intent(this, MyPetsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logout(){

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
}