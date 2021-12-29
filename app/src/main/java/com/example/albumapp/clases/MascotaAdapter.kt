package com.example.albumapp.clases

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.albumapp.R
import com.example.albumapp.controllers.ControladorMascotaImagen
import com.example.albumapp.databinding.PetRowBinding
import kotlinx.android.synthetic.main.pet_row.view.*

class MascotaAdapter (private val contexto : Context,
                      private val listaMascotas: List<Mascota>
) : ArrayAdapter<Mascota>(contexto, 0, listaMascotas) {

    private lateinit var binding: PetRowBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(contexto).inflate(R.layout.pet_row, parent, false)
        val mascota = listaMascotas[position]
        val uriImagen = ControladorMascotaImagen.obtenerUriImagen(contexto, mascota.idMascota.toLong())
        layout.txtPetName.setText(mascota.nombre)
        layout.petProfileRow.setImageURI(uriImagen)
        return layout
    }
}