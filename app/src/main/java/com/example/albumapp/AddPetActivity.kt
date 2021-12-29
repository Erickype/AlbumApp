package com.example.albumapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.albumapp.controllers.ControladorMascotaImagen
import com.example.albumapp.database.AppDatabase
import com.example.albumapp.databinding.ActivityAddPetBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddPetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPetBinding
    private val SELECCIONAR = 1
    private var uriImage: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarImagen()
    }

    private fun registrarMascota(){
        val database = AppDatabase.getDatabase(this)

        val nombre = binding.txtMascotaNombre.text.toString()
        val detalle = binding.txtMascotaDetalle.text.toString()

        val mascota = com.example.albumapp.clases.Mascota(nombre, detalle, R.drawable.placeholder)

        CoroutineScope(Dispatchers.IO).launch{
            val id = database.mascotas().insertarMascota(mascota)[0]
            uriImage?.let {
                ControladorMascotaImagen.guardarImagen(this@AddPetActivity, id, it)
            }
            this@AddPetActivity.finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.opGuardarMascota->{
                registrarMascota()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode ==SELECCIONAR && resultCode == Activity.RESULT_OK ->{
                uriImage = data!!.data
                binding.imgMascotaNuevo.setImageURI(uriImage)
            }
        }
    }

    private fun cargarImagen(){
        binding.imgMascotaNuevo.setOnClickListener{
            ControladorMascotaImagen.seleccionarFotoGaleria(this, SELECCIONAR)
        }

        binding.txtAddPhoto.setOnClickListener{
            ControladorMascotaImagen.seleccionarFotoGaleria(this, SELECCIONAR)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nueva_mascota_guardar, menu)
        return true
    }
}