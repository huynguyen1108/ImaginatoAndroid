package com.huyduc1108.imaginato.ui.login.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.huyduc1108.imaginato.ui.login.data.reponse.User

@Dao
interface LoginDao {
    @Query("SELECT * FROM User")
    fun getUserAll(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: User)
}