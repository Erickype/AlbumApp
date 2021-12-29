package com.example.albumapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.albumapp.clases.Mascota
import com.example.albumapp.clases.MascotaAdapter
import com.example.albumapp.database.AppDatabase
import com.example.albumapp.databinding.ActivityMyPetsBinding

class MyPetsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPetsBinding

    private lateinit var database: AppDatabase
    private lateinit var menu: Mascota
    private lateinit var menuLiveData: LiveData<Mascota>
    private val EDITAR = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPetsBinding.inflate(layoutInflater)
        setContentView(binding.root)
            click()

        cargarListaMascotas()
    }

    private fun cargarListaMascotas(){
        var listaMascotas = emptyList<Mascota>()
        val database = AppDatabase.getDatabase(this)

        database.mascotas().obtenerMascotas().observe(this, Observer {
            listaMascotas = it
            val adaptador = MascotaAdapter(this, listaMascotas)
            binding.lstMacotas.adapter = adaptador
        })
    }

    private fun click(){
        binding.btnNuevaMacota.setOnClickListener{
            val intent = Intent(applicationContext, AddPetActivity::class.java)
            startActivity(intent)
        }
    }
}