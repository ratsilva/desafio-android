package br.com.desafioandroid.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface OwnerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(owner: Owner)

}