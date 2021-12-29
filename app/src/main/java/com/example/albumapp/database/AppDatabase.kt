package com.example.albumapp.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.albumapp.clases.Mascota
import com.example.albumapp.interfaces.MascotasDAO


@Database(entities = [Mascota::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun mascotas(): MascotasDAO

    companion object {
        @Volatile
        private var INSTANCIA : AppDatabase? = null

        fun getDatabase(contexto: Context):AppDatabase{
            val tempInstancia = INSTANCIA
            if(tempInstancia != null){
                return tempInstancia
            }else{
                synchronized(this){
                    val instancia = Room.databaseBuilder(contexto, AppDatabase::class.java, "app_album").build()
                    INSTANCIA = instancia

                    return instancia
                }

            }
        }
    }
}