package br.com.desafioandroid.app

import android.app.Application
import androidx.room.Room
import br.com.desafioandroid.model.room.TembiciDatabase

class TembiciApplication : Application(){

    companion object {

        var database: TembiciDatabase? = null
        lateinit var instance: TembiciApplication

    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        database = Room.databaseBuilder(instance.applicationContext, TembiciDatabase::class.java, "tembici_database.db")
            .build()
    }

}