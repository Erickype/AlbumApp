package com.example.albumapp.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.albumapp.clases.Mascota

@Dao
interface MascotasDAO {
    @Query(value = "SELECT * FROM mascota")
    fun obtenerMascotas(): LiveData<List<Mascota>>

    @Query(value = "SELECT * FROM mascota WHERE idMascota = :id")
    fun obtenerMascota(id:Int): LiveData<Mascota>

    @Insert
    fun insertarMascota(vararg menu:Mascota): List<Long>

    @Update
    fun actualizarMascota(menu: Mascota)

    @Delete
    fun eliminarMascota(menu:Mascota)
}