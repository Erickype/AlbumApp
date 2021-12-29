package com.example.albumapp.clases

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="mascota")
class Mascota(
    val nombre:String,
    val descripcion: String,
    val imagen: Int,
    @PrimaryKey(autoGenerate = true)
    var idMascota: Int=0
): Serializable